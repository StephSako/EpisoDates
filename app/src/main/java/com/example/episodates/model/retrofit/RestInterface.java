package com.example.episodates.model.retrofit;

import com.example.episodates.model.response.Serie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Liste des différents appels REST
 */

public interface RestInterface {

    // Afficher les détail d'une série
    @GET("singlesearch/shows?embed=episodes")
    Call<Serie> serieDetails(@Query("q") String name_serie);
}