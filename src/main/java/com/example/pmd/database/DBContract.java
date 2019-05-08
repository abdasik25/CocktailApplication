package com.example.pmd.database;

import android.provider.BaseColumns;

final class DBContract implements BaseColumns {

    public static final String TABLE_NAME = "cocktail";
    public static final String COCKTAIL_ID = BaseColumns._ID;
    public static final String COCKTAIL_NAME = "name";
    public static final String COCKTAIL_GLASS = "glass";
    public static final String COCKTAIL_CATEGORY = "category";
    public static final String COCKTAIL_ALCOHOL = "alcohol";
}
