package com.example.episodates.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.episodates.R;
import com.example.episodates.controller.ResultSerieController;
import com.example.episodates.controller.SharedPreferencesController;
import com.example.episodates.model.adapters.AdapterRV_Episodes;
import com.example.episodates.model.response.Episode;
import com.example.episodates.model.response.Serie;

import java.text.DateFormat;
import java.util.List;

public class ResultSerieFragment extends Fragment implements View.OnClickListener {

    public static ResultSerieFragment newInstance() {
        return new ResultSerieFragment();
    }

    public SharedPreferencesController spc = new SharedPreferencesController();

    public ImageView IVImageSerie;
    public TextView TVname, TVgenres, TVwebchannel, TVLanguage, TVPremiered, TVDays, TVAverage, TVFutureDate;
    public ImageButton btnAdd;
    private RecyclerView rvFuturesEpisodes;

    private ResultSerieController serieController = new ResultSerieController(this);

    private String nameSerie;

    public void setNameSerie(String nameSerie){
        this.nameSerie = nameSerie;
    }

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.result_serie_fragment, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null){
            this.TVname = v.findViewById(R.id.name);
            this.TVgenres = v.findViewById(R.id.genres);
            this.TVwebchannel = v.findViewById(R.id.webchannel);
            this.TVLanguage = v.findViewById(R.id.language);
            this.TVPremiered = v.findViewById(R.id.premiered);
            this.TVDays = v.findViewById(R.id.days);
            this.TVAverage = v.findViewById(R.id.average);
            this.rvFuturesEpisodes = v.findViewById(R.id.rvFuturesEpisodes);
            this.IVImageSerie = v.findViewById(R.id.ivImageSerie);
            this.TVFutureDate = v.findViewById(R.id.futureDate);

            this.btnAdd = v.findViewById(R.id.btnAdd);
            this.btnAdd.setOnClickListener(this);

            serieController.getSerie(bundle.getString("nameSerie"));
        }

        return v;
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    public void displaySerie(Serie serie){
        if (serie != null) {
            TVname.setText("");
            TVwebchannel.setText("");
            TVgenres.setText("");
            TVLanguage.setText("");
            TVPremiered.setText("");
            TVDays.setText("");
            TVAverage.setText("");
            TVFutureDate.setText("");

            TVLanguage.setText(serie.getLanguage());
            TVAverage.setText(Float.toString(serie.getRating().getAverage()));
            TVname.setText(serie.getName());
            if (serie.getWebChannel() != null) TVwebchannel.setText(serie.getWebChannel().getName());
            else TVwebchannel.setText(serie.getNetwork().getName());
            TVgenres.setText(serie.getGenres().toString());

            DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
            if (serie.getPremiered() != null) TVPremiered.setText(dfl.format(serie.getPremiered()));
            else TVPremiered.setText("Première non renseignée");

            if (serie.getFutureDate() != null) TVFutureDate.setText(dfl.format(serie.getFutureDate()) + " " + serie.getSchedule().getTime());
            else{
                if (serie.getStatus().equals("Running")) TVFutureDate.setText("Date non communiquée");
                else TVFutureDate.setText("Série terminée");
            }

            TVDays.setText(serie.getSchedule().getDays().toString());

            Glide.with(this)
                    .load(serie.getImage().getOriginal())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher)
                            .fitCenter())
                    .into(IVImageSerie);
        }
    }

    public void showFuturesEpisodes(List<Episode> futureEpisodes){
        if (futureEpisodes != null && futureEpisodes.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            rvFuturesEpisodes.setLayoutManager(layoutManager);
            RecyclerView.Adapter mAdapter = new AdapterRV_Episodes(futureEpisodes, this);
            rvFuturesEpisodes.setAdapter(mAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAdd){
            if (!spc.get_AL_into_S(this.getActivity()).contains(nameSerie)) {
                spc.addNameSerieIntoSP(nameSerie, this.getActivity());
                btnAdd.setImageResource(R.mipmap.ic_unlike_foreground);
            }
            else{
                spc.deleteNameSerieIntoSP(nameSerie, this.getActivity());
                btnAdd.setImageResource(R.mipmap.ic_like_foreground);
            }
        }
    }
}