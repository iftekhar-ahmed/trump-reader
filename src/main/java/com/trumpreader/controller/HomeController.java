package com.trumpreader.controller;

import com.trumpreader.beans.FeedMessage;
import com.trumpreader.service.RssFeedPullParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class HomeController {
    private final String RSS_URL_LATEST = "http://rss.cnn.com/rss/cnn_latest.rss";

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String getArticles(Model map) {
        try {
            RssFeedPullParser parser = new RssFeedPullParser(RSS_URL_LATEST);
            List<FeedMessage> feedMessages = parser.readFeed();
            String[] phrases = {"Donald", "Trump"};
            for (FeedMessage feedMessage : feedMessages) {
                if (feedMessage.hasKeywords(phrases))
                    System.out.println(feedMessage);
            }
            map.addAttribute("result", feedMessages);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "home";
    }
}
