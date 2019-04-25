package com.example.episodates.view.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballapi.R;
import com.example.footballapi.controleur.TeamController;
import com.example.footballapi.model.model_recyclerview.classement.AdapterRV_Classement;
import com.example.footballapi.model.model_recyclerview.matches.AdapterRV_Matches;
import com.example.footballapi.view.fragments.MatchesFragment;
import com.example.footballapi.view.fragments.SquadFragment;

public class SearchSerieActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables qui seront transmises dans la vue Player
    public int idTeam = -1;
    public String nomClub = "";
    public String crestURLPlayer = "";

    public Button btnSquad;
    public Button btnMatches;
    public TextView tvWebSite;
    public TextView tvStade;
    public TextView tvActiveCompetitions;
    public TextView tvEntraineur;
    public ImageView logo_club;

    public boolean loadingPicsTeam;

    private MatchesFragment matchesFragment;
    Bundle bundle = new Bundle();
    private SquadFragment squadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choseseriename);
        TeamController teamcontroller = new TeamController(this);

        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);

        this.btnSquad = findViewById(R.id.btnSquad);
        this.btnMatches = findViewById(R.id.btnMatches);

        this.btnSquad.setOnClickListener(this);
        this.btnMatches.setOnClickListener(this);

        this.tvWebSite = findViewById(R.id.tvWebsite);
        this.tvStade = findViewById(R.id.tvStade);
        this.tvActiveCompetitions = findViewById(R.id.tvActiveCompetitions);
        this.tvEntraineur = findViewById(R.id.tvEntraineur);

        logo_club = findViewById(R.id.logo_club);

        // On récupère l'id de l'équipe qui peut soit venir de la liste dans le classement, soit de celle des matches
        Intent intent = getIntent();

        // L'ID de l'équipe peut venir de deux endroits : du classement, d'où on a cliquée, ou comme vu au dessus du fragment des matches.
        if ((idTeam = intent.getIntExtra(AdapterRV_Classement.CLE_DONNEES_ID_TEAM, -1)) == -1)
            idTeam = intent.getIntExtra(AdapterRV_Matches.CLE_DONNEES_ID_TEAM, -1);

        teamcontroller.onCreate(getString(R.string.token));

        // On affiche le fragment de la liste des matches par défaut (on change la couleur du bouton
        btnMatches.setBackgroundResource(R.color.green);
        btnSquad.setBackgroundResource(R.color.grey_desactivated);

        btnSquad.setEnabled(true);
        btnMatches.setEnabled(false);

        matchesFragment = MatchesFragment.newInstance();
        bundle.putInt("idTeam", idTeam);
        matchesFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.idFragmentSquad_Match, matchesFragment).commit();
    }

    public void onClick(View v) { // On remplace le fragment par celui géré par le bouton cliqué
        if (v.getId() == R.id.btnSquad) { // On affiche la liste des joueurs de l'équipe

            btnSquad.setEnabled(false);
            btnMatches.setEnabled(true);

            btnMatches.setBackgroundResource(R.color.grey_desactivated);
            btnSquad.setBackgroundResource(R.color.green);

            if (squadFragment == null){
                squadFragment = SquadFragment.newInstance();
                bundle.putInt("idTeam", idTeam);
                bundle.putString("crestURL", crestURLPlayer);
                squadFragment.setArguments(bundle);
            }

            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left)
                    .replace(R.id.idFragmentSquad_Match,squadFragment).commit();
        }
        else if (v.getId() == R.id.btnMatches) { // On affiche la liste des matches de l'équipe

            btnSquad.setEnabled(true);
            btnMatches.setEnabled(false);

            btnSquad.setBackgroundResource(R.color.grey_desactivated);
            btnMatches.setBackgroundResource(R.color.green);

            bundle.putInt("idTeam", idTeam);
            matchesFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_from_left, R.anim.slide_to_right)
                    .replace(R.id.idFragmentSquad_Match,matchesFragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Récupérer les valeurs choisies
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        // Préférences du switch pour afficher les logos
        this.loadingPicsTeam = sharedPref.getBoolean("logosTeam", true);
    }

    // Affichage du menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Écouteur sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // L’item sur lequel l’utilisateur a cliqué
        int id = item.getItemId();
        // Afficher le fragment des préférences
        if (id == R.id.pref) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.credits) {
            Intent intent = new Intent(this, CreditsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }
        else if (id == R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
            return true;
        }
        else if (id == R.id.search) {
            Intent intent = new Intent(this, SearchTeamActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}