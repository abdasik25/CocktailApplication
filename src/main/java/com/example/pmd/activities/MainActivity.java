package com.example.pmd.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.lab_3.R;
import com.example.pmd.data.Cocktail;
import com.example.pmd.database.DBHelper;
import com.example.pmd.events.AddCocktailEvent;
import com.example.pmd.events.ButtonsMainMenuEvent;
import com.example.pmd.fragments.AddCocktailFragment;
import com.example.pmd.fragments.ButtonsMainFragment;
import com.example.pmd.fragments.Cleanable;
import com.example.pmd.fragments.CocktailsListFragment;
import com.example.pmd.fragments.GalleryFragment;

public class MainActivity extends AppCompatActivity implements ButtonsMainMenuEvent,
        AddCocktailEvent, Cleanable {

    private FrameLayout fragmentContainer;
    private FragmentTransaction fragmentTransaction;
    private AddCocktailFragment addCocktailFragment;
    private ButtonsMainFragment buttonsMainFragment;
    private GalleryFragment galleryFragment;
    private CocktailsListFragment cocktailsListFragment;
    private DBHelper cocktailDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCocktailFragment = new AddCocktailFragment();
        buttonsMainFragment = new ButtonsMainFragment();
        galleryFragment = new GalleryFragment();
        cocktailsListFragment = new CocktailsListFragment();

        fragmentContainer = findViewById(R.id.frgmContainer);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(fragmentContainer.getId(), buttonsMainFragment);
        fragmentTransaction.commit();

        cocktailDBHelper = new DBHelper(this);
    }


    @Override
    public void buttonMainMenuClicked(int id) {
        switch (id) {
            case R.id.buttonAddCocktail:
                replaceFragment(addCocktailFragment);
                break;
            case R.id.buttonGallery:
                replaceFragment(galleryFragment);
                break;
            case R.id.buttonViewCocktailList:
                cocktailsListFragment.setAdapterList(cocktailDBHelper.getAllCocktails());
                replaceFragment(cocktailsListFragment);
                break;
            case R.id.buttonClearDatabase:
                clean();
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentContainer.getId(), fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void buttonAddCocktailClicked(Cocktail cocktail) {
        cocktailDBHelper.addCocktail(cocktail);
    }

    @Override
    public void clean() {
        cocktailDBHelper.deleteAllCocktails();
    }
}
