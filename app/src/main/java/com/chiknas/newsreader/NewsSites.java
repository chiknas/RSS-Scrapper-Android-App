package com.chiknas.newsreader;

/**
 * created by NikosK on 12/9/2019
 */
public enum NewsSites {
    KATHIMERINI_MAIN("https://www.kathimerini.gr/rss"),
    KATHIMERINI_GREEK_ECONOMY("https://www.kathimerini.gr/rss?i=news.el.ellhnikh-oikonomia"),
    KATHIMERINI_GREEK_REAL_ESTATE("https://www.kathimerini.gr/rss?i=news.el.realestate"),
    TOVIMA_GREEK_ECONOMY("https://www.tovima.gr/category/finance/feed/");


    private String feedUrl;

    NewsSites(String url){
        this.feedUrl = url;
    }

    public String getFeedUrl() {
        return feedUrl;
    }
}
