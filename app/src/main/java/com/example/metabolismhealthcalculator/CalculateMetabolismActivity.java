package com.example.metabolismhealthcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class CalculateMetabolismActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_metabolism);

        EditText inptAge = findViewById(R.id.inptAge);
        EditText inptHeight = findViewById(R.id.inptHeight);
        EditText inptWeight = findViewById(R.id.inptWeight);
        TextView bmrOutputTxt = findViewById(R.id.bmrOutputTxt);
        TextView tdeeOutputTxt = findViewById(R.id.tdeeOutputTxt);
        Button btnCalcMetab = findViewById(R.id.btnCalcMetab);

        // ======================
        // Initialize the Spinner
        // ----------------------
        Spinner spinnerWorkout = findViewById(R.id.spinnerWorkout);
        Spinner spinnerGender = findViewById(R.id.spinnerGender);

        // ============================================================================
        // Create an ArrayAdapter using the string array and the default spinner layout
        // ----------------------------------------------------------------------------
        ArrayAdapter<CharSequence> adapterWorkout = ArrayAdapter.createFromResource(
                this, R.array.workout_options, R.layout.spinner_item);

        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(
                this, R.array.gender_options, R.layout.spinner_item);

        // ==========================================================
        // Specify the layout to use when the list of choices appears
        // ----------------------------------------------------------
        adapterWorkout.setDropDownViewResource(R.layout.spinner_item);
        adapterGender.setDropDownViewResource(R.layout.spinner_item);


        // ================================
        // Apply the adapter to the Spinner
        // --------------------------------
        spinnerWorkout.setAdapter(adapterWorkout);
        spinnerGender.setAdapter(adapterGender);

        // =============================================
        // Set a listener for the Spinner item selection
        // ---------------------------------------------
        spinnerWorkout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Perform an action based on the selected item, for example:
                // Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is required but can be left empty if not used
            }
        });
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Perform an action based on the selected item, for example:
                // Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This method is required but can be left empty if not used
            }
        });

        btnCalcMetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMRAndTDEE(spinnerWorkout, spinnerGender, inptAge, inptHeight, inptWeight, bmrOutputTxt, tdeeOutputTxt);
            }
        });
    }
    private double getTDEEMultiplier(int workoutDays) {
        double multiplier;
        switch (workoutDays) {
            case 1: // 5 days a week
                multiplier = 2.075;
                break;
            case 2: // 4 days a week
                multiplier = 1.9;
                break;
            case 3: // 3 days a week
                multiplier = 1.725;
                break;
            case 4: // 2 days a week
                multiplier = 1.55;
                break;
            case 5: // 1 day a week
                multiplier = 1.375;
                break;
            default: // Do not workout
                multiplier = 1.2;
                break;
        }
        return multiplier;
    }
    private void calculateBMRAndTDEE(Spinner spinnerWorkout, Spinner spinnerGender, EditText inptAge, EditText inptHeight, EditText inptWeight, TextView bmrOutputTxt, TextView tdeeOutputTxt) {
        int age = Integer.parseInt(inptAge.getText().toString());
        float height = Float.parseFloat(inptHeight.getText().toString());
        float weight = Float.parseFloat(inptWeight.getText().toString());
        String gender = spinnerGender.getSelectedItem().toString();
        int workoutDays = spinnerWorkout.getSelectedItemPosition() + 1;

        // BMR calculation (Mifflin-St Jeor equation)
        double bmr = 10 * weight + 6.25 * height - 5 * age + (gender.equals("Male") ? 5 : -161);
        double tdee = bmr * getTDEEMultiplier(workoutDays);

        bmrOutputTxt.setText("BMR: " + String.format("%.2f", bmr));
        tdeeOutputTxt.setText("TDEE: " + String.format("%.2f", tdee));
    }
}