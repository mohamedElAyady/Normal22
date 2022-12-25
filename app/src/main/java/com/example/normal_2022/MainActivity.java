package com.example.normal_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.normal_2022.Model.City;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listV;
    EditText city,popul;
    Button add;
    String a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Population");

        listV = findViewById(R.id.listV);
        city = (EditText)findViewById(R.id.city);
        popul = (EditText)findViewById(R.id.popul);
        add = findViewById(R.id.add);

        ArrayList<City> cities =new ArrayList<City>();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = city.getText().toString();
                b = popul.getText().toString();
                City city = new City();
                city.setLabel(a);
                city.setPopulation(b);
                myRef.child(a).setValue(city);
                cities.clear();
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postsnapshot : snapshot.getChildren() ) {
                    City city = postsnapshot.getValue(City.class);
                    cities.add(city);
                }
                Adapter adapter =new Adapter(cities);
                listV.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s = (String) listV.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this,Update_city.class);
                intent.putExtra("city",s);
                startActivity(intent);
            }
        });

    }
}