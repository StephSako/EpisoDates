package com.example.episodates.view.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.episodates.R;
import com.example.episodates.view.fragments.SearchedSerieFragment;

public class SearchSerieActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText ETNameSerie;
    public Button btnSearchSerie;

    SearchedSerieFragment searchedSerieFragment;
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_serie_by_name_activity);

        ETNameSerie = findViewById(R.id.input_name_serie);

        btnSearchSerie = findViewById(R.id.btnSearchSerie);
        btnSearchSerie.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSearchSerie && !ETNameSerie.getText().toString().equals("")) {
            searchedSerieFragment = SearchedSerieFragment.newInstance();
            bundle.putString("nameSerie", ETNameSerie.getText().toString());
            searchedSerieFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.searchedSerieFragment,searchedSerieFragment).commit();
        }
    }

    // Affichage du menu
    /*@Override
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
        return super.onOptionsItemSelected(item);
    }*/
}