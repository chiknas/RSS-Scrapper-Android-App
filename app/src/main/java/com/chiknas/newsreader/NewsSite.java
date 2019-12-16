package com.chiknas.newsreader;

import android.content.Context;
import android.content.res.Resources;

/**
 * created by NikosK on 12/9/2019
 */
public enum NewsSite {
    KATHIMERINI_GREEK_ECONOMY(R.string.kathimerini_greek_economy, "https://www.kathimerini.gr/rss?i=news.el.ellhnikh-oikonomia"),
    NEWSBEAST_GREEK_ECONOMY(R.string.newsbeast_greek_economy, "https://www.newsbeast.gr/financial/feed"),
    KATHIMERINI_GREEK_REAL_ESTATE(R.string.kathimerini_real_estate, "https://www.kathimerini.gr/rss?i=news.el.realestate"),
    TOVIMA_GREEK_ECONOMY(R.string.tovima_greek_economy, "https://www.tovima.gr/category/finance/feed/");

    private int title;
    private String feedUrl;

    NewsSite(int title, String url) {
        this.title = title;
        this.feedUrl = url;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public String getTitle(Context context) {
        Resources res = context.getResources();
        return res.getString(this.title);
    }
}
