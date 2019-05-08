package com.example.pmd.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lab_3.R;
import com.example.pmd.events.ButtonsMainMenuEvent;

public class ButtonsMainFragment extends Fragment {
    private Button btnAddCocktail;
    private Button btnGallery;
    private Button btnCocktailList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.buttons_main, null);
        ButtonsMainMenuEvent activity = (ButtonsMainMenuEvent) getActivity();

        btnAddCocktail = view.findViewById(R.id.buttonAddCocktail);
        btnGallery = view.findViewById(R.id.buttonGallery);
        btnCocktailList = view.findViewById(R.id.buttonViewCocktailList);

        btnAddCocktail.setOnClickListener((v) -> activity.buttonMainMenuClicked(v.getId()));
        btnGallery.setOnClickListener((v) -> activity.buttonMainMenuClicked(v.getId()));
        btnCocktailList.setOnClickListener((v) -> activity.buttonMainMenuClicked(v.getId()));

        return view;
    }
}
