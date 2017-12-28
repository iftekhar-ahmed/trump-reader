package com.trumpreader.service;

import com.trumpreader.beans.FeedMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.net.MalformedURLException;
import java.util.List;

public class ArticleCrawlerService {

    private final String RSS_URL_TOP = "http://rss.cnn.com/rss/edition.rss";
    private final String RSS_URL_LATEST = "http://rss.cnn.com/rss/cnn_latest.rss";

    // private RestTemplate restTemplate;

    @Scheduled(fixedRate = 3600000)
    void print() {
        // System.out.println("Now time is " + new Date());
        /*if (restTemplate == null) {
            restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(RSS_URL_TOP, String.class);
            System.out.println(response);
        }*/
        /*try {
            RssFeedPullParser parser = new RssFeedPullParser(RSS_URL_LATEST);
            List<FeedMessage> feedMessages = parser.readFeed();
            String[] phrases = {"Donald", "Trump"};
            for (FeedMessage feedMessage : feedMessages) {
                if (feedMessage.hasKeywords(phrases))
                    System.out.println(feedMessage);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
    }
}
