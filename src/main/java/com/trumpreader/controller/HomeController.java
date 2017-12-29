package com.trumpreader.controller;

import com.trumpreader.RssFeedPullParser;
import com.trumpreader.beans.FeedMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final String RSS_URL_LATEST = "http://rss.cnn.com/rss/cnn_latest.rss";
    private final String TWITTER_API_KEY = "KMk3wGpKv64xjLXjES4vetyg8";
    private final String TWITTER_API_SECRET = "41Dy2KU5OiLmysBWFEC3aFwMsftPqObzAe6SrHSeoh2UDuFZ99";
    private final String TWITTER_ACCESS_TOKEN = "946589643482394624-rY1cgTt88F8lGMAMwE6aswLZZU2zCHT";
    private final String TWITTER_ACCESS_TOKEN_SECRET = "ZM4Jg1i6fJUV7Rki7vbRNFWVbN7GMgjW8rhs6LEOf63rC";
    private final String TWITTER_HANDLE_TRUMP = "realDonaldTrump";

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String getArticles(Model map) {
        // Get CNN feed
        try {
            RssFeedPullParser parser = new RssFeedPullParser(RSS_URL_LATEST);
            List<FeedMessage> feedMessages = parser.readFeed();
            String[] phrases = {"Donald", "Trump"};
            List<FeedMessage> trumpInFeed = new ArrayList<>();
            for (FeedMessage feedMessage : feedMessages) {
                if (feedMessage.hasKeywords(phrases)) {
                    trumpInFeed.add(feedMessage);
                }
            }
            map.addAttribute("feeds", trumpInFeed);
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
            List<Status> tweets = twitter.getUserTimeline(TWITTER_HANDLE_TRUMP);
            map.addAttribute("tweets", tweets);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return "home";
    }
}
