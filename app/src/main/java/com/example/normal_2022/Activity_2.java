package com.example.normal_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.normal_2022.Model.Ville;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_2 extends AppCompatActivity {
    //database var
    FirebaseDatabase database ;
    DatabaseReference myRef;
    TextView ville;
    TextView prec;
    TextView temp;
    Ville v;
    ArrayList<Ville> list;

    EditText Edittxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ville = findViewById(R.id.ville);
        prec = findViewById(R.id.prec);
        temp = findViewById(R.id.temp);

        Edittxt = (EditText)findViewById(R.id.edittxt);

        Intent intent = getIntent();
        String s = intent.getStringExtra("item");

        ville.setText(s);

        myRef =  FirebaseDatabase.getInstance().getReference("Meteo");



        /*
        v = new Ville("Agadir","24","15");
        myRef.child("Meteo").child(v.getLabel()).setValue(v);

        v = new Ville("Marrakech","23","20");
        myRef.child("Meteo").child(v.getLabel()).setValue(v);

        v = new Ville("Casablanka","27","56");
        myRef.child("Meteo").child(v.getLabel()).setValue(v);

        v = new Ville("Rabat","20","85");
        myRef.child("Meteo").child(v.getLabel()).setValue(v);


        v = new Ville("Taroudant","14","96");
        myRef.child("Meteo").child(v.getLabel()).setValue(v);
*/

        System.out.println(s);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren() ) {
                    String a= postSnapshot.child("température").getValue(String.class);
                    String b= postSnapshot.child("précipitation").getValue(String.class);

                    prec.setText(b + "%");
                    temp.setText(a + "°C");

                    Edittxt.setText(b);

                    if (postSnapshot.child("label").getValue(String.class).equals(s)) break;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getMessage());
            }
        });






    }
}