package com.trumpreader.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class ArticleCrawlerService {

    @Scheduled(fixedRate = 5000)
    void print() {
        System.out.println("Now time is " + new Date());
    }
}
