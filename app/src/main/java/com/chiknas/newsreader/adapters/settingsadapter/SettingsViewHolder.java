package com.chiknas.newsreader.adapters.settingsadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Switch;
import com.chiknas.newsreader.R;

/**
 * created by NikosK on 12/16/2019
 */
public class SettingsViewHolder extends RecyclerView.ViewHolder {

    private Switch siteSwitch;

    public SettingsViewHolder(View itemView) {
        super(itemView);
        this.siteSwitch = itemView.findViewById(R.id.siteSwitch);
    }

    public Switch getSiteSwitch() {
        return siteSwitch;
    }
}
