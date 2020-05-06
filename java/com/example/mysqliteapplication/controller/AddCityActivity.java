package com.example.mysqliteapplication.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysqliteapplication.R;
import com.example.mysqliteapplication.model.City;
import com.example.mysqliteapplication.model.DataStore;

public class AddCityActivity extends AppCompatActivity {

    TextView textViewCityName;
    TextView textViewPopulation;
    Button buttonEditCity;
    Button buttonDeleteCity;

    City city;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        textViewCityName = findViewById(R.id.textViewCityName);
        textViewPopulation = findViewById(R.id.textViewPopulation);
        buttonEditCity = findViewById(R.id.buttonEditCity);

        buttonDeleteCity = findViewById(R.id.buttonDeleteCity);
        buttonDeleteCity.setVisibility(View.GONE);

        if (getIntent().hasExtra("city")) {
            city = getIntent().getExtras().getParcelable("city");
            String pos = getIntent().getExtras().getString("position");
            position = Integer.parseInt(pos);
        }
        if(city != null){
            buttonDeleteCity.setVisibility(View.VISIBLE);
            textViewCityName.setText(city.getName());
            int pop = city.getPopulation();
            textViewPopulation.setText(String.valueOf(pop));
        }



        buttonEditCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city != null){
                    String cityName = textViewCityName.getText().toString();
                    String cityPopulation = textViewPopulation.getText().toString();
                    City newCity = new City(cityName, Integer.parseInt(cityPopulation));
                    DataStore.getInstance().RemoveCity(position);
                    DataStore.getInstance().addCity(newCity);
                    Intent intent = new Intent(AddCityActivity.this,
                            MainActivity.class
                    );
                    intent.putExtra("changed", "edited");
                    startActivity(intent);
                }else{
                    String cityName = textViewCityName.getText().toString();
                    String cityPopulation = textViewPopulation.getText().toString();
                    City newCity = new City(cityName,Integer.parseInt(cityPopulation));
                    DataStore.getInstance().addCity(newCity);
                    Intent intent = new Intent(AddCityActivity.this,
                            MainActivity.class
                    );
                    intent.putExtra("changed", "created");
                    startActivity(intent);
                }
            }
        });

        buttonDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataStore.getInstance().RemoveCity(position);
                Intent intent = new Intent(AddCityActivity.this,
                        MainActivity.class
                );
                intent.putExtra("changed", "deleted");
                startActivity(intent);
            }
        });
    }
}
