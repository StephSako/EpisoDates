package com.example.episodates.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.episodates.R;
import com.example.episodates.controller.SerieController;
import com.example.episodates.model.response.Serie;

public class SearchedSerieFragment extends Fragment {

    public static SearchedSerieFragment newInstance() {
        return new SearchedSerieFragment();
    }

    public TextView TVname, TVgenres, TVwebchannel, TVstatus, TVNetwork, TVLanguage, TVPremiered, TVTime, TVDays, TVAverage;

    private SerieController serieController = new SerieController(this);

    public String nameSerie;

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.searchedseriefragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            this.TVname = v.findViewById(R.id.name);
            this.TVgenres = v.findViewById(R.id.genres);
            this.TVstatus = v.findViewById(R.id.status);
            this.TVwebchannel = v.findViewById(R.id.webchannel);
            this.TVTime = v.findViewById(R.id.time);
            this.TVLanguage = v.findViewById(R.id.language);
            this.TVNetwork = v.findViewById(R.id.network);
            this.TVPremiered = v.findViewById(R.id.premiered);
            this.TVDays = v.findViewById(R.id.days);
            this.TVAverage = v.findViewById(R.id.average);

            nameSerie = bundle.getString("nameSerie");

            serieController.onCreate(nameSerie);
        }

        return v;
    }

    @SuppressLint("SetTextI18n")
    public void displaySerie(Serie serie){
        if (serie != null) {
            TVname.setText("");
            TVstatus.setText("");
            TVwebchannel.setText("");
            TVgenres.setText("");
            TVLanguage.setText("");
            TVPremiered.setText("");
            TVNetwork.setText("");
            TVDays.setText("");
            TVTime.setText("");
            TVAverage.setText("");

            TVLanguage.setText(serie.getLanguage());
            TVTime.setText(serie.getSchedule().getTime());
            TVAverage.setText(Float.toString(serie.getRating().getAverage()));
            TVname.setText(serie.getName());
            TVstatus.setText(serie.getStatus());
            if (serie.getWebChannel() != null) TVwebchannel.setText(serie.getWebChannel().getName());
            else TVNetwork.setText(serie.getNetwork().getName());
            TVgenres.setText(serie.getGenres().toString());
            TVPremiered.setText(serie.getPremiered());
            TVDays.setText(serie.getSchedule().getDays().toString());
        }
    }
}