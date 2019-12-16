package com.chiknas.newsreader.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.chiknas.newsreader.R;
import com.chiknas.newsreader.adapters.feedadapter.FeedAdapter;
import com.chiknas.newsreader.adapters.feedadapter.FeedEntryClickListener;
import com.chiknas.newsreader.services.RssScrapperService;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FeedEntryClickListener {

    public static final int RSS_REQUEST_DOWNLOAD_CODE = 99;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
                List<SyndEntry> entries = (List<SyndEntry>) data.getSerializableExtra(RssScrapperService.RSS_SORTED_BY_DATE);
                ((FeedAdapter) recyclerView.getAdapter()).setDataset(entries, this);
                recyclerView.getAdapter().notifyDataSetChanged();
            case RssScrapperService.ERROR_CODE:
                //do nothing for now
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View.OnClickListener onFeedEntryClick(final String url) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_refresh:
                startFeedService();
                break;

            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
