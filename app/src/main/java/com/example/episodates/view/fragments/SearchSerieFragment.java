package com.example.episodates.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.episodates.R;

public class SearchSerieFragment extends Fragment implements View.OnClickListener {

    public EditText ETNameSerie;
    public Button btnSearchSerie;

    ResultSerieFragment searchedSerieFragment;
    Bundle bundle = new Bundle();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.search_serie_fragment, container, false);

        setHasOptionsMenu(true);

        ETNameSerie = v.findViewById(R.id.input_name_serie);
        ETNameSerie.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchedSerieFragment = ResultSerieFragment.newInstance();
                    bundle.putString("nameSerie", ETNameSerie.getText().toString());
                    searchedSerieFragment.setArguments(bundle);
                    getChildFragmentManager().beginTransaction().replace(R.id.searchedSerieFragment, searchedSerieFragment).commit();
                    InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        btnSearchSerie = v.findViewById(R.id.btnSearchSerie);
        btnSearchSerie.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnSearchSerie && !ETNameSerie.getText().toString().equals("")) {
            searchedSerieFragment = ResultSerieFragment.newInstance();
            bundle.putString("nameSerie", ETNameSerie.getText().toString());
            searchedSerieFragment.setArguments(bundle);
            getChildFragmentManager().beginTransaction().replace(R.id.searchedSerieFragment, searchedSerieFragment).commit();
            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}