package com.example.episodates.controller;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.episodates.model.response.Serie;
import com.example.episodates.model.retrofit.Rest;
import com.example.episodates.view.fragments.ResultSerieFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieController {

    private ResultSerieFragment fragment;

    public SerieController(ResultSerieFragment fragment) {
        this.fragment = fragment;
    }

    public void onCreate(final String name_serie) {
        Call<Serie> call = Rest.get().serieDetails(name_serie);
        call.enqueue(new Callback<Serie>() {

            @Override
            public void onResponse(@NonNull Call<Serie> call, @NonNull Response<Serie> response) {
                if (response.isSuccessful()) {
                    Serie serie = response.body();
                    assert serie != null;

                    Date today = new Date();
                    Date futureDate = null;
                    for(int i = 0; i < serie.get_embedded().getEpisodes().size(); i++) {

                        if (today.before(serie.get_embedded().getEpisodes().get(i).getAirdate())) {
                            futureDate = serie.get_embedded().getEpisodes().get(i).getAirdate();
                            break;
                        }
                    }

                    fragment.showFuturesEpisodes(serie.get_embedded().getEpisodes());
                    fragment.displaySerie(serie, futureDate);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Serie> call, @NonNull Throwable t) {

                // On affiche le classement récupéré depuis la base de données locale en mode hors ligne
                /*DataBase database = new DataBase(activity);
                List<TeamDAO> classementDAO = database.findClassementById(activity.idCompet);

                if (classementDAO.size() > 0) { // Si la BD locale n'a jamais été initialisée

                    Objects.requireNonNull(activity).setTitle(classementDAO.get(0).getNomCompet() + " - [Hors ligne]");

                    List<TeamModel> listFinal = new ArrayList<>();

                    // On remplit les lignes (le classement d'id 0 représente le classement total du championnat)
                    for (int i = 0; i < classementDAO.size(); i++) {
                        TeamModel model = new TeamModel();

                        model.setPosition(String.valueOf(classementDAO.get(i).getPosition()));
                        model.setName(classementDAO.get(i).getClub_name());
                        model.setDiff(String.valueOf(classementDAO.get(i).getDiff()));
                        model.setPoints(String.valueOf(classementDAO.get(i).getPoints()));
                        model.setIdTeam(String.valueOf(classementDAO.get(i).getDiff()));
                        listFinal.add(model);
                    }

                    // booléen qui active ou désactive les écouteurs sur les item de la recyclerview en cas de connexion oun non à internet
                    activity.showList(listFinal, false);
                }
                Toast.makeText(activity, "Classement non mis à jour.\nVérifiez votre connexion.", Toast.LENGTH_SHORT).show();*/
            }
        });
    }
}