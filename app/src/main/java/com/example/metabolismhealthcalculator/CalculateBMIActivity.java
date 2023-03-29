package com.example.metabolismhealthcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculateBMIActivity extends AppCompatActivity {
    private EditText inputHeight;
    private EditText inputWeight;
    private TextView calculatedOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        inputHeight = findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.InputWeight);
        calculatedOutput = findViewById(R.id.calculatedOutput);

        Button calculateButton = findViewById(R.id.calcBMI);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        double heightInInches = Double.parseDouble(inputHeight.getText().toString());
        double weightInPounds = Double.parseDouble(inputWeight.getText().toString());
        double bmi;

        // Calculate BMI using the formula: BMI = (weight in pounds / (height in inches * height in inches)) * 703
        bmi = (weightInPounds / (heightInInches * heightInInches)) * 703;

        // Round BMI to 2 decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        bmi = Double.parseDouble(decimalFormat.format(bmi));

        // Display the calculated BMI
        calculatedOutput.setText(getString(R.string.bmi_result) + " " + bmi);
    }
}
