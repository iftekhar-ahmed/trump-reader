package com.trumpreader.beans;

import com.sun.istack.internal.NotNull;

import java.util.regex.Pattern;

public class FeedMessage {

    private String title;
    private String link;
    private String description;
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public boolean hasKeywords(@NotNull String[] phrases) {
        if (phrases.length > 0) {
            for (String phrase : phrases) {
                Pattern pattern = Pattern.compile(Pattern.quote(phrase), Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(title).find() || pattern.matcher(description).find())
                    return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Feed [description=" + description
                + ", link=" + link + ", pubDate="
                + pubDate + ", title=" + title + "]";
    }
}
