package com.chiknas.newsreader.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chiknas.newsreader.R;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * created by NikosK on 12/7/2019
 */
public class FeedAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<SyndEntry> entries = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_entry_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SyndEntry syndEntry = entries.get(position);
        holder.title.setText(syndEntry.getTitle());
        holder.description.setText(syndEntry.getDescription().getValue());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setDataset(List<SyndEntry> entries) {
        this.entries = entries;
    }


}

class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView title;
    public TextView description;

    public ViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
    }
}
