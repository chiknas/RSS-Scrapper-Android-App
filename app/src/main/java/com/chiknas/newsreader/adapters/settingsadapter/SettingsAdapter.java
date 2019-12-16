package com.chiknas.newsreader.adapters.settingsadapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chiknas.newsreader.NewsSite;
import com.chiknas.newsreader.R;

/**
 * created by NikosK on 12/16/2019
 */
public class SettingsAdapter extends RecyclerView.Adapter<SettingsViewHolder> {

    private Context context;
    private NewsSite[] sites;
    private SharedPreferences sharedPreferences;


    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        this.sites = NewsSite.values();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(parent.getContext());

        View v = LayoutInflater.from(this.context)
                .inflate(R.layout.settings_item_view, parent, false);

        return new SettingsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        NewsSite site = sites[position];
        holder.getSiteSwitch().setText(site.getTitle(this.context));
        holder.getSiteSwitch().setChecked(sharedPreferences.getBoolean(site.name(), false));
        holder.getSiteSwitch().setOnCheckedChangeListener((compoundButton, b) ->
                sharedPreferences.edit().putBoolean(site.name(), b).apply());
    }

    @Override
    public int getItemCount() {
        return NewsSite.values().length;
    }
}
