package com.example.pmd.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Drinks {
    @SerializedName("drinks")
    @Expose
    ArrayList<Cocktail> cocktails;

    public Cocktail getCocktail(int index) {
        return cocktails == null ? null : cocktails.get(index);
    }
}
