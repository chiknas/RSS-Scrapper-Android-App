package com.chiknas.newsreader.adapters.feedadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.chiknas.newsreader.R;

/**
 * created by NikosK on 12/8/2019
 */
public class EntryViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView site;
    private TextView description;
    private View itemView;

    public EntryViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.title = itemView.findViewById(R.id.title);
        this.site = itemView.findViewById(R.id.site);
        this.description = itemView.findViewById(R.id.description);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getSite() {
        return site;
    }

    public TextView getDescription() {
        return description;
    }

    public View getItemView() {
        return itemView;
    }
}