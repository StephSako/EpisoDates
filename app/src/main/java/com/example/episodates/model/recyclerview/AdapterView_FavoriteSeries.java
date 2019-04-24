package com.example.episodates.model.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.episodates.model.display.DisplayedSerie;
import com.example.episodates.view.FavoriteSeriesView;
import com.example.footballapi.R;

import java.util.List;

public class AdapterView_FavoriteSeries extends RecyclerView.Adapter<AdapterView_FavoriteSeries.ViewHolder> {


    private List<DisplayedSerie> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvShirtNumber;
        TextView tvNationality;
        TextView tvPosition;

        public View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            tvPlayerName = v.findViewById(R.id.tvPlayerName);
            tvShirtNumber = v.findViewById(R.id.tvShirtNumber);
            tvNationality = v.findViewById(R.id.tvNationality);
            tvPosition = v.findViewById(R.id.tvPosition);
        }
    }

    public AdapterView_FavoriteSeries(List<DisplayedSerie> myDataset) {
        values = myDataset;
    }

    @NonNull
    @Override
    public AdapterView_FavoriteSeries.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_squad, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvPosition.setText(values.get(position).getPlayerPosition());
        holder.tvPlayerName.setText(values.get(position).getPlayerName());
        holder.tvNationality.setText(values.get(position).getPlayerNationality());
        holder.tvShirtNumber.setText(values.get(position).getPlayerShirtNumber());

        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, FavoriteSeriesView.class);
                //intent.putExtra(CLE_DONNEES_ID_PLAYER, Integer.parseInt(values.get(position).getPlayerId()));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return values.size();
    }

}