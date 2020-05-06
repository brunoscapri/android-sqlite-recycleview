package com.example.mysqliteapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class City implements Parcelable {
    private long id;
    private String name;
    private int population;


    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    protected City(Parcel in) {
        id = in.readLong();
        name = in.readString();
        population = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "City{" + "name: " + name + ", population: "+ population + "}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public static Comparator<City> byPopulation = new Comparator<City>() {
        @Override
        public int compare(City o1, City o2) {
            return + Integer.compare(o1.population, o2.population);
        }
    };

    public static Comparator<City> byName = new Comparator<City>() {
        @Override
        public int compare(City o1, City o2) {
            String name1 = o1.name;
            String name2 = o2.name;
            return name1.compareTo(name2);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(population);
    }
}
