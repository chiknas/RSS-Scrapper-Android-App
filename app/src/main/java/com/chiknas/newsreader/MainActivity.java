package com.chiknas.newsreader;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chiknas.newsreader.adapters.FeedAdapter;
import com.chiknas.newsreader.services.RssScrapperService;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RSS_REQUEST_DOWNLOAD_CODE = 99;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.feedScroller);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FeedAdapter());

        startFeedService();
    }

    private void startFeedService() {
        Intent rssScrapperIntent = new Intent(getApplicationContext(), RssScrapperService.class);
        PendingIntent rssResultPendingIntent = createPendingResult(RSS_REQUEST_DOWNLOAD_CODE, new Intent(), 0);
        rssScrapperIntent.putExtra(RssScrapperService.PENDING_RESULT_EXTRA, rssResultPendingIntent);
        startService(rssScrapperIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RssScrapperService.RESULT_CODE:
                List<SyndEntry> entries = (List<SyndEntry>) data.getSerializableExtra(RssScrapperService.RSS_RESULT_EXTRA);
                ((FeedAdapter) recyclerView.getAdapter()).setDataset(entries);
                recyclerView.getAdapter().notifyDataSetChanged();
            case RssScrapperService.ERROR_CODE:
                //do nothing for now
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
