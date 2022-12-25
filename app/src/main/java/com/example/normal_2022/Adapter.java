package com.example.normal_2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.normal_2022.Model.City;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Adapter extends BaseAdapter {
    TextView itempopul,itemcity;

    ArrayList<City> cities = new ArrayList<City>();

    public Adapter(ArrayList<City> cities) {
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public String getItem(int i) {
        return cities.get(i).getLabel();
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.item,null);
        itemcity = view.findViewById(R.id.itemcity);
        itempopul = view.findViewById(R.id.itempopul);

        itemcity.setText(cities.get(i).getLabel()+" :");
        itempopul.setText(cities.get(i).getPopulation());

        return view;
    }
}
