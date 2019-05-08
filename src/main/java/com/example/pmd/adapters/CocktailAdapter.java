package com.example.pmd.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab_3.R;
import com.example.pmd.data.Cocktail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.CocktailViewHolder> {

    private List<Cocktail> cocktailsList = new ArrayList<>();

    class CocktailViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCocktailId;
        private TextView txtCocktailName;
        private TextView txtCocktailCategory;
        private TextView txtCocktailGlass;
        private TextView txtCocktailAlcohol;

        public CocktailViewHolder(View itemView) {
            super(itemView);
            txtCocktailId = itemView.findViewById(R.id.txtCocktailID);
            txtCocktailName = itemView.findViewById(R.id.txtCocktailName);
            txtCocktailCategory = itemView.findViewById(R.id.txtCocktailCategory);
            txtCocktailGlass = itemView.findViewById(R.id.txtCocktailGlass);
            txtCocktailAlcohol = itemView.findViewById(R.id.txtAlcohol);
        }

        public void bind(Cocktail cocktail) {
            txtCocktailId.setText(Integer.toString(cocktail.getId()));
            txtCocktailName.setText(cocktail.getName());
            txtCocktailCategory.setText(cocktail.getCategory());
            txtCocktailGlass.setText(cocktail.getGlass());
            txtCocktailAlcohol.setText(cocktail.getAlcohol());
        }
    }

    public void addItem(Cocktail cocktail) {
        cocktailsList.add(cocktail);
        notifyDataSetChanged();
    }

    public void addItems(Collection<Cocktail> cocktails) {
        cocktailsList.addAll(cocktails);
        notifyDataSetChanged();
    }

    public void clearItems() {
        cocktailsList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CocktailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cocktail_item_view,
                viewGroup, false);
        return new CocktailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CocktailViewHolder cocktailViewHolder, int i) {
        cocktailViewHolder.bind(cocktailsList.get(i));
    }

    @Override
    public int getItemCount() {
        return cocktailsList.size();
    }

    public List<Cocktail> getCocktailsList() {
        return cocktailsList;
    }
}
