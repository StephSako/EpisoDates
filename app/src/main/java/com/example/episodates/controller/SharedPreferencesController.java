package com.example.episodates.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SharedPreferencesController {

    public ArrayList<String> get_AL_into_S(Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString("followedSeriesList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> list = gson.fromJson(json, type);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        return list;
    }

    private void save_AL_into_SP(ArrayList<String> list, Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        String json = gson.toJson(list);
        editor.putString("followedSeriesList", json);
        editor.apply();
    }

    public void addNameSerieIntoSP(String name_serie, Activity activity){
        ArrayList<String> list = get_AL_into_S(activity);
        list.add(name_serie);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        save_AL_into_SP(list, activity);
    }

    public void deleteNameSerieIntoSP(String name_serie, Activity activity){
        ArrayList<String> list = get_AL_into_S(activity);
        list.remove(name_serie);
        save_AL_into_SP(list, activity);
    }
}
