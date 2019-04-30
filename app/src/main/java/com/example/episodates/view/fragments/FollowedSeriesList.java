package com.example.episodates.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.episodates.R;
import com.example.episodates.controller.FollowedSeriesController;
import com.example.episodates.controller.ResultSerieController;
import com.example.episodates.model.adapters.AdapterRV_Episodes;
import com.example.episodates.model.adapters.AdapterRV_FollowedSeries;
import com.example.episodates.model.response.Episode;
import com.example.episodates.model.response.Serie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FollowedSeriesList extends Fragment {

    private RecyclerView rvFollowedSeries;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<Serie> followedSerieList = ArrayList<>();

    public static FollowedSeriesList newInstance() {
        return new FollowedSeriesList();
    }

    private FollowedSeriesController followedSerieController = new FollowedSeriesController(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.followed_series_list, container, false);

        this.rvFollowedSeries = v.findViewById(R.id.rvFollowedSeries);

        ArrayList<String> list = new ArrayList<>();
        list.add("test");
        list.add("table");
        list.add("chaise");

        if (followedSerieController.get_AL_into_S().size() > 0) followedSerieController.onCreate(nameSerie);

        for (Serie serieList : get_AL_into_S()){

        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this.getContext());

        Objects.requireNonNull(this.getContext()).getSharedPreferences("followedSeries", Context.MODE_PRIVATE);

        if (followedSerieController.get_AL_into_S().size() > 0) {
            for (String serieName : followedSerieController.get_AL_into_S()){
                followedSerieController.onCreate(serieName);
            }
            showFollowedSeries();
        }

        return v;
    }

    public void showFollowedSeries(){
        if (followedSerieList != null && followedSerieList.size() > 0) {
            // Define an adapter
            layoutManager = new LinearLayoutManager(getContext());
            rvFollowedSeries.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_FollowedSeries(followedSerieList, this);
            rvFollowedSeries.setAdapter(mAdapter);
        }
    }

    public void addToFollowedSeriesList(Serie serie){
        this.followedSerieList.add(serie);
    }
}
