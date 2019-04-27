package com.example.episodates.model.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.episodates.R;
import com.example.episodates.model.response.Episode;
import com.example.episodates.view.fragments.ResultSerieFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterRV_Episodes extends RecyclerView.Adapter<AdapterRV_Episodes.ViewHolder> {

    private List<Episode> episodes;
    private ResultSerieFragment fragment;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvEpisodeName, tvSeasonEpisode, tvSummary;
        ImageView ivImageEpisode;

        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvDate = v.findViewById(R.id.tvDate);
            tvEpisodeName = v.findViewById(R.id.tvEpisodeName);
            tvSeasonEpisode = v.findViewById(R.id.tvSeasonEpisode);
            ivImageEpisode = v.findViewById(R.id.ivImageEpisode);
            tvSummary = v.findViewById(R.id.tvSummary);
        }
    }

    public AdapterRV_Episodes(List<Episode> episodes, ResultSerieFragment fragment) {
        this.episodes = episodes;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterRV_Episodes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_future_episodes, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
        holder.tvDate.setText(dfl.format(episodes.get(position).getAirdate()));
        holder.tvSeasonEpisode.setText("S" + episodes.get(position).getSeason() + " E" + episodes.get(position).getNumber());
        holder.tvEpisodeName.setText(episodes.get(position).getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.tvSummary.setText(Html.fromHtml(episodes.get(position).getSummary(), Html.FROM_HTML_MODE_COMPACT));
        }

        if (episodes.get(position).getImageEpisode() != null) {
            Glide.with(fragment)
                    .load(episodes.get(position).getImageEpisode().getOriginal())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .fitCenter())
                    .into(holder.ivImageEpisode);
        }


        /*holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, TeamActivity.class);
                intent.putExtra(CLE_DONNEES_ID_TEAM, Integer.parseInt(values.get(position).getIdTeam()));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

}