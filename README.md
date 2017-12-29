# trump-reader
Gets last 25 CNN articles referencing Donald Trump, plus his twitter timeline.

A web application that crawls the latest articles from CNN rss feed (http://rss.cnn.com/rss/cnn_latest.rss) that has
either `Donald` or `Trump` as keywords (case-insensitive) and pulls statuses from his twitter handle @realDonaldTrump.

There is an additional feature apart from these. You can type in a keyword or hint in an input box and get random quotes Donald
Trump made which contain your keywords. These quotes are collected from https://www.tronalddump.io/ and tagged with
genuine sources.

The application uses [Java Spring MVC framework 4.3.0](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
and servlets for main application, [twitter4j](http://twitter4j.org/en/) for Twitter api and [Gson](https://github.com/google/gson) library for Json parsing. 
It runs on a Tomcat server.
