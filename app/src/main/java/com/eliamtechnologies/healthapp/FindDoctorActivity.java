package com.eliamtechnologies.healthapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView physician = findViewById(R.id.cardFDPhysician);
        physician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Physician");
                startActivity(it);
            }
        });

        CardView dietitian = findViewById(R.id.cardFDDietitian);
        dietitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Dietitian");
                startActivity(it);
            }
        });

        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Dentist");
                startActivity(it);
            }
        });

        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Surgeon");
                startActivity(it);
            }
        });

        CardView cardiologist = findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Cardiologist");
                startActivity(it);
            }
        });


    }
}