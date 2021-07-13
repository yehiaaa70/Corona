package com.example.side.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.side.R;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void dr(View view) {
        Intent dr=new Intent(getApplicationContext(),com.example.side.ui.logout.registar_dr.class);
        startActivity(dr);
    }

    public void patient(View view) {

        Intent patient=new Intent(getApplicationContext(),com.example.side.ui.logout.register_patient.class);
        startActivity(patient);
    }

    public void nurse(View view) {

        Intent nurse=new Intent(getApplicationContext(),com.example.side.ui.logout.register_nurse.class);
        startActivity(nurse);
    }

    public void equip(View view) {
        Intent equip=new Intent(getApplicationContext(),com.example.side.ui.logout.register_equipment.class);
        startActivity(equip);


    }
}
