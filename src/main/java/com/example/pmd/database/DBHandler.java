package com.example.pmd.database;

import com.example.pmd.data.Cocktail;

import java.util.List;

public interface DBHandler {
    boolean addCocktail(Cocktail cocktail);
    List<Cocktail> getAllCocktails();
    void deleteAllCocktails();
}
