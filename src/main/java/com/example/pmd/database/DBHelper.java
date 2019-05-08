package com.example.pmd.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pmd.data.Cocktail;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper implements DBHandler {

    private final static String DATABASE_NAME = "DrinksDatabase";
    private final static int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createRequest = "CREATE TABLE " + DBContract.TABLE_NAME
                + " (" + DBContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DBContract.COCKTAIL_NAME + " VARCHAR(50) NOT NULL, "
                + DBContract.COCKTAIL_CATEGORY + " VARCHAR(50) NOT NULL, "
                + DBContract.COCKTAIL_GLASS + " VARCHAR(50) NOT NULL, "
                + DBContract.COCKTAIL_ALCOHOL + " VARCHAR(50) NOT NULL);";

        db.execSQL(createRequest);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Database update from " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public boolean addCocktail(Cocktail cocktail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBContract.COCKTAIL_NAME, cocktail.getName());
        contentValues.put(DBContract.COCKTAIL_CATEGORY, cocktail.getCategory());
        contentValues.put(DBContract.COCKTAIL_GLASS, cocktail.getGlass());
        contentValues.put(DBContract.COCKTAIL_ALCOHOL, cocktail.getAlcohol());

        long result = db.insert(DBContract.TABLE_NAME, null, contentValues);

        return result == -1 ? false : true;
    }

    @Override
    public List<Cocktail> getAllCocktails() {
        List<Cocktail> cocktails = new ArrayList<>();
        String query = "SELECT * FROM " + DBContract.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Cocktail cocktail = new Cocktail();
                cocktail.setId(cursor.getInt(0));
                cocktail.setName(cursor.getString(1));
                cocktail.setCategory(cursor.getString(2));
                cocktail.setGlass(cursor.getString(3));
                cocktail.setAlcohol(cursor.getString(4));
                cocktails.add(cocktail);
            } while (cursor.moveToNext());
        }

        return cocktails;
    }

    @Override
    public void deleteAllCocktails() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBContract.TABLE_NAME, null, null);
        db.close();
    }
}
