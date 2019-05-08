package com.example.pmd.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lab_3.R;
import com.example.pmd.adapters.CocktailAdapter;
import com.example.pmd.data.Cocktail;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CocktailsListFragment extends Fragment {

    private RecyclerView cocktailsRecyclerView;
    private CocktailAdapter cocktailAdapter = new CocktailAdapter();
    private Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cocktails_list, null);
        cocktailsRecyclerView = view.findViewById(R.id.cocktailsRecyclerView);
        cocktailsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        cocktailsRecyclerView.setAdapter(cocktailAdapter);
        saveButton = view.findViewById(R.id.buttonSaveToExternalStorage);
        saveButton.setOnClickListener((v) -> saveToExternalStorage());
        return view;
    }

    private void saveToExternalStorage() {
        if (isExternalStorageWritable() &&
                isPermissionsGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            File jsonFile = new File(getPublicAlarmsStorageDirectory(), "cocktails.json");
            File txtFile = new File(getPublicAlarmsStorageDirectory(), "cocktails.txt");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter txtWriter = new FileWriter(txtFile);
                 FileWriter jsonWriter = new FileWriter(jsonFile)) {
                List<Cocktail> cocktailsList = cocktailAdapter.getCocktailsList();
                String jsonStr = gson.toJson(cocktailsList);

                jsonWriter.write(jsonStr);

                for (Cocktail cocktail : cocktailsList) {
                    txtWriter.write(cocktail.toString());
                }

                Toast.makeText(getContext(),
                        "Files have been saved!", Toast.LENGTH_SHORT).show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(),
                    "External storage is not available", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private boolean isPermissionsGranted(String permission) {
        if (ContextCompat.checkSelfPermission(getContext(), permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return false;
        }
        return true;
    }

    public void setAdapterList(List<Cocktail> cocktails) {
        cocktailAdapter.clearItems();
        cocktailAdapter.addItems(cocktails);
    }

    private File getPublicAlarmsStorageDirectory() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS),
                getResources().getString(R.string.app_name));
        if (!file.exists() && !file.mkdirs()) {
            Toast.makeText(getContext(), "Directory not created!", Toast.LENGTH_LONG).show();
        }
        return file;
    }

    public void addCocktail(Cocktail cocktail) {
        cocktailAdapter.addItem(cocktail);
    }

}
