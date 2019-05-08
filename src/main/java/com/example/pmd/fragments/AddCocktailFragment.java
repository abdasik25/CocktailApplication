package com.example.pmd.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab_3.R;
import com.example.pmd.activities.MainActivity;
import com.example.pmd.data.Cocktail;
import com.example.pmd.data.Drinks;
import com.example.pmd.events.ButtonsMainMenuEvent;
import com.example.pmd.service.NetworkCocktailService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCocktailFragment extends Fragment implements Cleanable {
    private TextView mID;
    private TextView mName;
    private TextView mCategory;
    private TextView mGlass;
    private TextView mAlcohol;
    private Button buttonAdd;
    private Button buttonRandom;

    private Cocktail cocktail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_cocktail, null, false);

        mID = view.findViewById(R.id.txtID);
        mName = view.findViewById(R.id.txtName);
        mCategory = view.findViewById(R.id.txtCategory);
        mGlass = view.findViewById(R.id.txtGlass);
        mAlcohol = view.findViewById(R.id.txtAlcohol);
        buttonAdd = view.findViewById(R.id.btnAdd);
        buttonRandom = view.findViewById(R.id.getFromAPIButton);

        buttonAdd.setOnClickListener((v -> buttonAddClicked()));
        buttonRandom.setOnClickListener((v ->
                buttonRandomClicked(mAlcohol.getText().toString())));

        return view;
    }

    private void buttonRandomClicked(String drink) {
        NetworkCocktailService.getInstance()
                .getJsonApi()
                .getCocktailIDByAlcoholDrink(drink)
                .enqueue(new Callback<Drinks>() {
                    @Override
                    public void onResponse(@NonNull Call<Drinks> call,
                                           @NonNull Response<Drinks> response) {
                        Drinks drinks = response.body();
                        NetworkCocktailService.getInstance()
                                .getJsonApi()
                                .getCocktailInfoByID(drinks.getCocktail(0).getId())
                                .enqueue(new Callback<Drinks>() {
                                    @Override
                                    public void onResponse(Call<Drinks> call,
                                                           Response<Drinks> response) {
                                        Drinks drinks = response.body();
                                        cocktail = drinks.getCocktail(0);
                                        mID.setText(cocktail.getId() + "");
                                        mCategory.setText(cocktail.getCategory());
                                        mGlass.setText(cocktail.getGlass());
                                        mName.setText(cocktail.getName());
                                    }

                                    @Override
                                    public void onFailure(Call<Drinks> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                    }

                    @Override
                    public void onFailure(@NonNull Call<Drinks> call, @NonNull Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    private void buttonAddClicked() {

        if (mID.getText() == "") {
            return;
        }

        Cocktail cocktail = new Cocktail(Integer.parseInt(mID.getText().toString()),
                mName.getText().toString(), mGlass.getText().toString(),
                mCategory.getText().toString(), mAlcohol.getText().toString());

        ButtonsMainMenuEvent buttonsMainEvent = (MainActivity) getActivity();
        ((MainActivity) buttonsMainEvent).buttonAddCocktailClicked(cocktail);
        clean();

    }

    @Override
    public void clean() {
        mID.setText("");
        mName.setText("");
        mGlass.setText("");
        mCategory.setText("");
        mAlcohol.setText("");
    }
}
