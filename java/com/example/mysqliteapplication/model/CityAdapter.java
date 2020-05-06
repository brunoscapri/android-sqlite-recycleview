package com.example.mysqliteapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysqliteapplication.R;
import com.example.mysqliteapplication.controller.AddCityActivity;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {

    private List<City> cities = DataStore.getInstance().getCities();
    Context context;



    @NonNull
    @Override
    public CityAdapter.CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_cities, parent, false);

        return new CityHolder(view);

    }

    public CityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.CityHolder holder, final int position) {

        final City city = cities.get(position);
        holder.textViewCityName.setText(city.getName());
        holder.textViewCityPopulation.setText("" + city.getPopulation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,
                        AddCityActivity.class
                );
                String pos = String.valueOf(position);
                System.out.println(pos);
                intent.putExtra("city", city);
                intent.putExtra("position", pos);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityHolder extends RecyclerView.ViewHolder{
        TextView textViewCityName;
        TextView textViewCityPopulation;

        public CityHolder(View itemView) {
            super(itemView);
            textViewCityName = itemView.findViewById(R.id.textViewCityName);
            textViewCityPopulation = itemView.findViewById(R.id.textViewCityPopulation);

        }

    }
}
