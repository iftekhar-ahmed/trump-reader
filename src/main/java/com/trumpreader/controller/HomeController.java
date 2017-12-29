package com.trumpreader.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.trumpreader.RssFeedPullParser;
import com.trumpreader.beans.FeedMessage;
import com.trumpreader.beans.Quote;
import com.trumpreader.beans.QuoteQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final String RSS_URL_LATEST = "http://rss.cnn.com/rss/cnn_latest.rss";
    private final String QUOTES_URL = "https://api.tronalddump.io/search/quote?query=";
    private final String TWITTER_API_KEY = "KMk3wGpKv64xjLXjES4vetyg8";
    private final String TWITTER_API_SECRET = "41Dy2KU5OiLmysBWFEC3aFwMsftPqObzAe6SrHSeoh2UDuFZ99";
    private final String TWITTER_ACCESS_TOKEN = "946589643482394624-rY1cgTt88F8lGMAMwE6aswLZZU2zCHT";
    private final String TWITTER_ACCESS_TOKEN_SECRET = "ZM4Jg1i6fJUV7Rki7vbRNFWVbN7GMgjW8rhs6LEOf63rC";
    private final String TWITTER_HANDLE_TRUMP = "realDonaldTrump";

    @RequestMapping(name = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String getArticles(@ModelAttribute("query") QuoteQuery query, BindingResult bindingResult,
                              Model model, HttpServletRequest httpServletRequest) {

        String method = httpServletRequest.getMethod();
        if (RequestMethod.POST.name().equals(method)) {
            model.addAttribute("quote", getTrumpQuote(query));
        }

        // Get CNN feed
        try {
            RssFeedPullParser parser = new RssFeedPullParser(RSS_URL_LATEST);
            List<FeedMessage> feedMessages = parser.readFeed();
            final String[] phrases = {"Donald", "Trump"};
            List<FeedMessage> trumpFeeds
                    = feedMessages.stream().filter(p -> p.hasKeywords(phrases)).limit(25).collect(Collectors.toList());
            model.addAttribute("feeds", trumpFeeds);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Get Twitter timeline
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(TWITTER_API_KEY)
                .setOAuthConsumerSecret(TWITTER_API_SECRET)
                .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            List<Status> tweets = twitter.getUserTimeline(TWITTER_HANDLE_TRUMP, new Paging(1, 25));
            model.addAttribute("handle", TWITTER_HANDLE_TRUMP);
            model.addAttribute("tweets", tweets);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return "home";
    }

    /**
     * Gets a list of quotes from https://www.tronalddump.io
     *
     * @param query text to search against quote
     * @return {@link Quote} object, null if query is empty or no quotes are found
     */
    private Quote getTrumpQuote(QuoteQuery query) {
        String queryText = query.getText();
        if (queryText == null || queryText.isEmpty()) return null;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String response = restTemplate.exchange(QUOTES_URL + queryText, HttpMethod.GET, entity, String.class).getBody();
        Type listType = new TypeToken<List<Quote>>() {
        }.getType();
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        if (jsonObject.get("count").getAsInt() == 0)
            return null;
        String quotesArray = jsonObject.getAsJsonObject("_embedded").getAsJsonArray("quotes").toString();
        List<Quote> quotes = gson.fromJson(quotesArray, listType);
        int size = quotes.size();
        if (size > 0) {
            if (size == 1)
                return quotes.get(0);
            Random r = new Random();
            int lowerBound = 0;
            int upperBound = size - 1;
            int randomIndex = r.nextInt(upperBound - lowerBound) + lowerBound;
            return quotes.get(randomIndex);
        }
        return null;
    }
}
