package com.example.episodates.model.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.episodates.view.fragments.FollowedSeriesList;
import com.example.episodates.view.fragments.SearchSerieFragment;

public class Adapter_ViewPager extends FragmentStatePagerAdapter {

    public Adapter_ViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new FollowedSeriesList();
            case 1: return new SearchSerieFragment();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Followed series";
            case 1: return "Search";
            default: return null;
        }
    }
}
