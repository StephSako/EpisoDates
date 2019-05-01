package com.example.episodates.controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPreferencesController {

    public ArrayList<String> get_AL_into_S(Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString("followedSeriesList", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void save_AL_into_SP(ArrayList<String> list, Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("followedSeriesList", json);
        editor.apply();
    }

    public void addNameSerieIntoSP(String name_serie, Activity activity){
        ArrayList<String> list = get_AL_into_S(activity);
        list.add(name_serie);
        save_AL_into_SP(list, activity);
        Toast.makeText(activity, get_AL_into_S(activity).toString(), Toast.LENGTH_SHORT).show();
    }

    public void deleteNameSerieIntoSP(String name_serie, Activity activity){
        ArrayList<String> list = get_AL_into_S(activity);
        list.remove(name_serie);
        save_AL_into_SP(list, activity);
        Toast.makeText(activity, get_AL_into_S(activity).toString(), Toast.LENGTH_SHORT).show();
    }
}
