package com.example.metabolismhealthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start Music Service
        Intent musicServiceIntent = new Intent(this, MusicService.class);
        startService(musicServiceIntent);

        //Add a listener to the button to take you to the temp converter
        Button btnEnter=(Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          goEnter();
                                      }
                                  }
        );

    }

    private void goEnter() {
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        this.startActivity(intent);
    }

    // Add onDestroy method to stop the music service when the app is closed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent musicServiceIntent = new Intent(this, MusicService.class);
        stopService(musicServiceIntent);
    }
}