package com.example.episodates.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.episodates.R;
import com.example.episodates.controller.ResultSerieController;
import com.example.episodates.model.adapters.AdapterRV_Episodes;
import com.example.episodates.model.response.Episode;
import com.example.episodates.model.response.Serie;

import java.text.DateFormat;
import java.util.List;

public class ResultSerieFragment extends Fragment {

    public static ResultSerieFragment newInstance() {
        return new ResultSerieFragment();
    }

    public ImageView IVImageSerie;
    public TextView TVname, TVgenres, TVwebchannel, TVLanguage, TVPremiered, TVTime, TVDays, TVAverage, TVFutureDate;
    private RecyclerView rvFuturesEpisodes;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ResultSerieController serieController = new ResultSerieController(this);

    public String nameSerie;

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.result_serie_fragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            this.TVname = v.findViewById(R.id.name);
            this.TVgenres = v.findViewById(R.id.genres);
            this.TVwebchannel = v.findViewById(R.id.webchannel);
            this.TVTime = v.findViewById(R.id.time);
            this.TVLanguage = v.findViewById(R.id.language);
            this.TVPremiered = v.findViewById(R.id.premiered);
            this.TVDays = v.findViewById(R.id.days);
            this.TVAverage = v.findViewById(R.id.average);
            this.rvFuturesEpisodes = v.findViewById(R.id.rvFuturesEpisodes);
            this.IVImageSerie = v.findViewById(R.id.ivImageSerie);
            this.TVFutureDate = v.findViewById(R.id.futureDate);

            nameSerie = bundle.getString("nameSerie");

            serieController.onCreate(nameSerie);
        }

        return v;
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    public void displaySerie(Serie serie){
        if (serie != null) {
            TVname.setText("");
            TVwebchannel.setText("");
            TVgenres.setText("");
            TVLanguage.setText("");
            TVPremiered.setText("");
            TVDays.setText("");
            TVTime.setText("");
            TVAverage.setText("");
            TVFutureDate.setText("");

            TVLanguage.setText(serie.getLanguage());
            TVTime.setText(serie.getSchedule().getTime());
            TVAverage.setText(Float.toString(serie.getRating().getAverage()));
            TVname.setText(serie.getName());
            if (serie.getWebChannel() != null) TVwebchannel.setText(serie.getWebChannel().getName());
            else TVwebchannel.setText(serie.getNetwork().getName());
            TVgenres.setText(serie.getGenres().toString());

            DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
            TVPremiered.setText(dfl.format(serie.getPremiered()));

            if (serie.getFutureDate() != null) TVFutureDate.setText(dfl.format(serie.getFutureDate()));
            else{
                if (serie.getStatus().equals("Running")) TVFutureDate.setText("Date non communiquée");
                else TVFutureDate.setText("Série terminée");
            }

            TVDays.setText(serie.getSchedule().getDays().toString());

            Glide.with(this)
                    .load(serie.getImage().getOriginal())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .fitCenter())
                    .into(IVImageSerie);
        }
    }

    public void showFuturesEpisodes(List<Episode> futureEpisodes){
        if (futureEpisodes != null && futureEpisodes.size() > 0) {
            // Define an adapter
            layoutManager = new LinearLayoutManager(getContext());
            rvFuturesEpisodes.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_Episodes(futureEpisodes, this);
            rvFuturesEpisodes.setAdapter(mAdapter);
        }
    }
}