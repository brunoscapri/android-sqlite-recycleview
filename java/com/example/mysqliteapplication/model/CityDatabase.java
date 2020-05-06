package com.example.mysqliteapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CityDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "cities.sqlite";
    private static final String DB_TABLE = "cities";
    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_POPULATION = "population";
    private static final int DB_VERSION = 1;

    private Context context;

    public CityDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + "(" + COL_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " +
                COL_POPULATION + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long createCityInDatabase(City city){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, city.getName());
        values.put(COL_POPULATION, city.getPopulation());
        long id = sqLiteDatabase.insert(DB_TABLE, "", values);
        city.setId(id);
        sqLiteDatabase.close();
        return id;
    }

    public int updateCityFromDatabase(City city){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, city.getName());
        values.put(COL_POPULATION, city.getPopulation());
        String _id = String.valueOf(city.getId());
        int count = sqLiteDatabase.update(DB_TABLE, values, COL_ID + "=?", new String[]{_id});
        sqLiteDatabase.close();
        return count;
    }

    public int removeCityFromDatabase(City city){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String _id = String.valueOf(city.getId());
        int count = sqLiteDatabase.delete(DB_TABLE, COL_ID + "=?", new String[]{_id});
        sqLiteDatabase.close();
        return count;
    }

    public List<City> retrieveCitiesFromDatabase(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
          DB_TABLE, null, null, null, null, null, COL_NAME
        );
        List<City>  cities = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                City city = new City(
                        cursor.getString(cursor.getColumnIndex(COL_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COL_POPULATION))
                );
                city.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                cities.add(city);
            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();

        return cities;
    }


}
