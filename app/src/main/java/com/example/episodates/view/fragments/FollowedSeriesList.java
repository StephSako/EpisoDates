package com.example.episodates.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.episodates.R;
import com.example.episodates.controller.FollowedSeriesController;
import com.example.episodates.controller.SharedPreferencesController;
import com.example.episodates.model.adapters.AdapterRV_FollowedSeries;
import com.example.episodates.model.response.Serie;

import java.util.ArrayList;
import java.util.Objects;

public class FollowedSeriesList extends Fragment {

    private RecyclerView rvFollowedSeries;

    public SharedPreferencesController spc = new SharedPreferencesController();

    private FollowedSeriesController followedSerieController = new FollowedSeriesController(this);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.followed_series_list, container, false);

        this.rvFollowedSeries = v.findViewById(R.id.rvFollowedSeries);

        Objects.requireNonNull(this.getContext()).getSharedPreferences("followedSeries", Context.MODE_PRIVATE);

        fillTest();

        followedSerieController.onCreate();

        return v;
    }

    public void showFollowedSeries(ArrayList<Serie> followedSerieList){
        if (followedSerieList != null && followedSerieList.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvFollowedSeries.setLayoutManager(layoutManager);
            RecyclerView.Adapter mAdapter = new AdapterRV_FollowedSeries(followedSerieList, this);
            rvFollowedSeries.setAdapter(mAdapter);
        }
    }

    public void fillTest(){
        ArrayList<String> listTest = new ArrayList<>();
        listTest.add("Stranger Things");
        listTest.add("13 Reasons Why");
        listTest.add("Black Mirror");
        listTest.add("Game of Thrones");
        spc.save_AL_into_SP(listTest, this.getActivity());
    }
}
