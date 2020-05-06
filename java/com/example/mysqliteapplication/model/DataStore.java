package com.example.mysqliteapplication.model;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

public class DataStore {
    private static DataStore instance = null;
    private DataStore(){}
    public static DataStore getInstance(){
        if(instance == null)
            instance = new DataStore();

        return instance;
    }

    private CityDatabase database;
    private List<City> cities;
    private Context context;

    public List<City> getCities() {
        return cities;
    }

    public void setContext(Context context){
        this.context = context;
        database = new CityDatabase(context);
        cities = database.retrieveCitiesFromDatabase();

    }

    public void addCity(City city){
        if(database.createCityInDatabase(city) > 0){
            cities.add(city);

        }else{
            Toast.makeText(context, "addCity problem", Toast.LENGTH_LONG).show();
        }
    }

    public void editCity(City city,int position){
        int count = database.updateCityFromDatabase(city);
        if(count > 0){
            cities.set(position, city);
        }
    }

    public void RemoveCity(int position){
       int count = database.removeCityFromDatabase(cities.get(position));
       if(count > 0){
           cities.remove(position);
       }
    }

    public int getCityListSize(){
        return cities.size();
    }
}
