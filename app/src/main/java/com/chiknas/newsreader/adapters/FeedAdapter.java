package com.chiknas.newsreader.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chiknas.newsreader.R;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * created by NikosK on 12/7/2019
 */
public class FeedAdapter extends RecyclerView.Adapter<EntryViewHolder> {
    FeedEntryClickListener entryClickListener;
    private List<SyndEntry> entries = new ArrayList<>();

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_entry_view, parent, false);

        return new EntryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final EntryViewHolder holder, int position) {
        SyndEntry syndEntry = entries.get(position);
        holder.getTitle().setClickable(true);
        holder.getTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.getDescription().setVisibility(holder.getDescription().getVisibility() > View.VISIBLE
                        ? View.VISIBLE
                        : View.GONE);
            }
        });
        holder.getTitle().setText(syndEntry.getTitle());
        holder.getDescription().setText(syndEntry.getDescription().getValue());
        holder.getItemView().setOnClickListener(entryClickListener.onFeedEntryClick(syndEntry.getLink()));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setDataset(List<SyndEntry> entries, FeedEntryClickListener entryClickListener) {
        this.entries = entries;
        this.entryClickListener = entryClickListener;
    }
}
