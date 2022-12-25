package com.example.normal_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
   ArrayList<String> array;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        array = new ArrayList<String>();

        array.add("Taroudant");
        array.add("Agadir");
        array.add("Marrakech");
        array.add("Casablank");
        array.add("Rabat");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,array);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(MainActivity.this,Activity_2.class);
                intent.putExtra("item", (String) listView.getItemAtPosition(i));
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });



    }
}