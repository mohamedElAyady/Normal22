package com.example.normal_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.normal_2022.Model.City;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update_city extends AppCompatActivity {
    EditText edittxt1,edittxt2;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_city);


        edittxt1 = findViewById(R.id.edittxt1);
        edittxt2 = findViewById(R.id.edittxt2);
        update = findViewById(R.id.update);

        Intent intent = getIntent();
        String s = intent.getStringExtra("city");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Population");


        myRef.child(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    edittxt1.setText(s);
                    edittxt2.setText(snapshot.child("population").getValue(String.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = edittxt1.getText().toString();
                String b = edittxt2.getText().toString();
                City city = new City();
                city.setLabel(a);
                city.setPopulation(b);
                myRef.child(a).setValue(city);
                startActivity(new Intent(Update_city.this,MainActivity.class));
            }
        });


    }
}