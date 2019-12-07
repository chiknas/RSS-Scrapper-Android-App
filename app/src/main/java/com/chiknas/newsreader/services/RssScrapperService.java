package com.chiknas.newsreader.services;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * @author nkukn - created on 26/11/2019 7:16 μ.μ.
 */
public class RssScrapperService extends IntentService {

    public static final String PENDING_RESULT_EXTRA = "pending_result";
    public static final String RSS_RESULT_EXTRA = "url";
    public static final int RESULT_CODE = 0;
    public static final int ERROR_CODE = 1;
    private static final String TAG = RssScrapperService.class.getSimpleName();

    public RssScrapperService() {
        super(TAG);
    }

    public void romeTest(PendingIntent pendingIntent) {
        try {
            URL feedSource = new URL("https://www.kathimerini.gr/rss");
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            List<SyndEntry> entries = feed.getEntries();
            Intent intent = new Intent();
            intent.putExtra(RSS_RESULT_EXTRA, (Serializable) entries);

            pendingIntent.send(this, RESULT_CODE, intent);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //printMessage((PendingIntent) intent.getParcelableExtra(PENDING_RESULT_EXTRA));
        romeTest((PendingIntent) intent.getParcelableExtra(PENDING_RESULT_EXTRA));
    }
}
