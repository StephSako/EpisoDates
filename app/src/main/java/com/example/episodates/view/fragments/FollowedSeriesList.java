package com.example.episodates.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.episodates.R;
import com.example.episodates.controller.FollowedSeriesController;
import com.example.episodates.controller.SharedPreferencesController;
import com.example.episodates.model.adapters.AdapterRV_FollowedSeries;
import com.example.episodates.model.response.Serie;

import java.util.ArrayList;

public class FollowedSeriesList extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView rvFollowedSeries;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView.Adapter mAdapter;

    public SharedPreferencesController spc = new SharedPreferencesController();

    private FollowedSeriesController followedSerieController = new FollowedSeriesController(this);

    public SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.followed_series_list, container, false);

        setHasOptionsMenu(true);

        this.rvFollowedSeries = v.findViewById(R.id.rvFollowedSeries);
        mSwipeRefreshLayout = v.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        followedSerieController.getSeries();

        return v;
    }

    public void showFollowedSeries(ArrayList<Serie> followedSerieList){
        if (followedSerieList != null && followedSerieList.size() > 0) {
            layoutManager = new LinearLayoutManager(getContext());
            rvFollowedSeries.setLayoutManager(layoutManager);
            mAdapter = new AdapterRV_FollowedSeries(followedSerieList, this);
            rvFollowedSeries.setAdapter(mAdapter);
        }
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        followedSerieController.getSeries();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    // Écouteur sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            mSwipeRefreshLayout.setRefreshing(true);
            followedSerieController.getSeries();
            //mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
