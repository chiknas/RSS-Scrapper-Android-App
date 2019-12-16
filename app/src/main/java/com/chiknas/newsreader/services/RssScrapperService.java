package com.chiknas.newsreader.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import com.chiknas.newsreader.NewsSite;
import com.chiknas.newsreader.util.FeedEntriesComparator;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author nkukn - created on 26/11/2019 7:16 μ.μ.
 */
public class RssScrapperService extends IntentService {

    public static final String PENDING_RESULT_EXTRA = "pending_result";
    public static final String RSS_SORTED_BY_DATE = "url";
    public static final int RESULT_CODE = 0;
    public static final int ERROR_CODE = 1;
    private static final String TAG = RssScrapperService.class.getSimpleName();

    public RssScrapperService() {
        super(TAG);
    }

    public void getAllFeedEntries(PendingIntent pendingIntent) {
        try {
            List<SyndEntry> entries = new ArrayList<>();
            for (NewsSite site : EnumSet.allOf(NewsSite.class)) {
                SyndFeed feed = getFeed(site);
                if (feed != null) {
                    entries.addAll(feed.getEntries());
                }
            }
            entries.sort(new FeedEntriesComparator());

            Intent intent = new Intent();
            intent.putExtra(RSS_SORTED_BY_DATE, (Serializable) entries);
            pendingIntent.send(this, RESULT_CODE, intent);
        } catch (Exception e) {

        }
    }

    private boolean isFeedEnabled(NewsSite newsSite) {
        return PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getBoolean(newsSite.name(), false);
    }

    private SyndFeed getFeed(NewsSite site) {
        SyndFeed result = null;
        if (isFeedEnabled(site)) {
            try {
                URL feedSource = new URL(site.getFeedUrl());
                SyndFeedInput input = new SyndFeedInput();
                result = input.build(new XmlReader(feedSource));
            } catch (Exception e) {

            }
        }
        return result;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        getAllFeedEntries(intent.getParcelableExtra(PENDING_RESULT_EXTRA));
    }
}
