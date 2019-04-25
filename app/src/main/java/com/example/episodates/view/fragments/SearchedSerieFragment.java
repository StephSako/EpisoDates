package com.example.episodates.view.fragments;

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

    public TextView TVname, TVgenres, TVwebchannel, TVstatus;

    private SerieController serieController = new SerieController(this);

    public String nameSerie;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.searchedseriefragment, container, false);

        this.TVname = v.findViewById(R.id.name);
        this.TVgenres = v.findViewById(R.id.status);
        this.TVstatus = v.findViewById(R.id.genres);
        this.TVwebchannel = v.findViewById(R.id.webchannel);

        Bundle bundle = this.getArguments();
        if (bundle != null) nameSerie = bundle.getString("nameSerie");

        serieController.onCreate(nameSerie);
        return v;
    }

    public void displaySerie(Serie serie){
        if (serie != null) {
            TVname.setText(serie.getName());
            TVstatus.setText(serie.getStatus());
            TVwebchannel.setText(serie.getWebChannel().getName());
            TVgenres.setText(serie.getGenres().toString());
        }
    }
}