package com.example.pmd.data;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cocktail {
    @SerializedName("idDrink")
    @Expose
    private int id;

    @SerializedName("strDrink")
    @Expose
    private String name;

    @SerializedName("strGlass")
    @Expose
    private String glass;

    @SerializedName("strCategory")
    @Expose
    private String category;

    @SerializedName("strAlchohol")
    @Expose
    private String alcohol;

    public Cocktail(int id, String name, String glass, String category, String alcohol) {
        this.id = id;
        this.name = name;
        this.glass = glass;
        this.category = category;
        this.alcohol = alcohol;
    }

    public Cocktail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", glass='" + glass + '\'' +
                ", category='" + category + '\'' +
                ", alcohol='" + alcohol + '\'' +
                '}';
    }
}
