package com.example.metabolismhealthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Add a listener to the button to take you to the temp converter
        Button btnBMR=(Button) findViewById(R.id.btnBMR);
        btnBMR.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              goBMR();
                                          }
                                      }
        );

        Button btnMetab=(Button) findViewById(R.id.btnMetab);
        btnMetab.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           goMetab();
                                       }
                                   }
        );
    }

    private void goBMR() {
        Intent intent = new Intent(MainMenuActivity.this, CalculateBMRActivity.class);
        this.startActivity(intent);
    }

    private void goMetab() {
        Intent intent = new Intent(MainMenuActivity.this, CalculateMetabolismActivity.class);
        this.startActivity(intent);
    }

}