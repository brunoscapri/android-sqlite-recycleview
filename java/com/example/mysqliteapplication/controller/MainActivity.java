package com.example.mysqliteapplication.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.mysqliteapplication.R;
import com.example.mysqliteapplication.model.City;
import com.example.mysqliteapplication.model.CityAdapter;
import com.example.mysqliteapplication.model.DataStore;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewCities;
    Button buttonAdd;
    Button buttonSortPopulation;
    Button buttonSortName;
    CityAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cidades");

        recyclerViewCities = findViewById(R.id.recyclerViewCities);
        DataStore.getInstance().setContext(this);
        adapter = new CityAdapter(this);
        recyclerViewCities.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCities.setLayoutManager(manager);
        buttonAdd  = findViewById(R.id.buttonAdd);
        buttonSortPopulation  = findViewById(R.id.buttonSortPopulation);
        buttonSortName  = findViewById(R.id.buttonSortName);

        if (getIntent().hasExtra("changed")) {
            adapter.notifyDataSetChanged();
        }


        buttonSortPopulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(DataStore.getInstance().getCities(), City.byPopulation);
                adapter.notifyDataSetChanged();
            }
        });

        buttonSortName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(DataStore.getInstance().getCities(), City.byName);
                adapter.notifyDataSetChanged();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,
                        AddCityActivity.class
                );
                //intent.putExtra()
                startActivity(intent);
            }
        });
    }
}
