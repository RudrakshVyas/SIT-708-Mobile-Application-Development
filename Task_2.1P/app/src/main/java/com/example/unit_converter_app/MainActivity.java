package com.example.unit_converter_app;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner sourceUnit, destUnit;
    Button convertBtn;
    TextView resultText;

    String[] lengthUnits = {"Inch", "Foot", "Yard", "Mile", "Centimeter", "Kilometer"};
    String[] weightUnits = {"Pound", "Ounce", "Ton", "Gram", "Kilogram"};
    String[] tempUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        sourceUnit = findViewById(R.id.sourceUnit);
        destUnit = findViewById(R.id.destUnit);
        convertBtn = findViewById(R.id.convertBtn);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, mergeUnits());
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnit.setAdapter(unitAdapter);
        destUnit.setAdapter(unitAdapter);

        convertBtn.setOnClickListener(view -> convertUnits());
    }

    private String[] mergeUnits() {
        String[] all = new String[lengthUnits.length + weightUnits.length + tempUnits.length];
        int index = 0;
        for (String unit : lengthUnits) all[index++] = unit;
        for (String unit : weightUnits) all[index++] = unit;
        for (String unit : tempUnits) all[index++] = unit;
        return all;
    }

    private void convertUnits() {
        String inputStr = inputValue.getText().toString().trim();
        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        try {
            double input = Double.parseDouble(inputStr);
            String from = sourceUnit.getSelectedItem().toString();
            String to = destUnit.getSelectedItem().toString();

            if (from.equals(to)) {
                resultText.setText("Same units. Result: " + input);
                return;
            }

            // Ensure units are from the same category
            if (!isSameCategory(from, to)) {
                resultText.setText("Incompatible units. Select units from the same category.");
                return;
            }

            double result = performConversion(input, from, to);
            resultText.setText(String.format("Converted: %.4f", result));
        } catch (NumberFormatException e) {
            resultText.setText("Invalid input. Please enter a numeric value.");
        }
    }

    private boolean isSameCategory(String from, String to) {
        return (isLength(from) && isLength(to)) ||
                (isWeight(from) && isWeight(to)) ||
                (isTemp(from) && isTemp(to));
    }

    private boolean isLength(String unit) {
        for (String u : lengthUnits) {
            if (u.equals(unit)) return true;
        }
        return false;
    }

    private boolean isWeight(String unit) {
        for (String u : weightUnits) {
            if (u.equals(unit)) return true;
        }
        return false;
    }

    private boolean isTemp(String unit) {
        for (String u : tempUnits) {
            if (u.equals(unit)) return true;
        }
        return false;
    }

    private double performConversion(double value, String from, String to) {
        // Convert input to a base unit first
        switch (from) {
            // Length to cm
            case "Inch": value *= 2.54; break;
            case "Foot": value *= 30.48; break;
            case "Yard": value *= 91.44; break;
            case "Mile": value *= 160934; break;
            case "Kilometer": value *= 100000; break;

            // Weight to kg
            case "Pound": value *= 0.453592; break;
            case "Ounce": value *= 0.0283495; break;
            case "Ton": value *= 907.185; break;
            case "Gram": value /= 1000; break;

            // Temperature conversions
            case "Fahrenheit":
                if (to.equals("Celsius")) return (value - 32) / 1.8;
                if (to.equals("Kelvin")) return ((value - 32) / 1.8) + 273.15;
                break;
            case "Celsius":
                if (to.equals("Fahrenheit")) return (value * 1.8) + 32;
                if (to.equals("Kelvin")) return value + 273.15;
                break;
            case "Kelvin":
                if (to.equals("Celsius")) return value - 273.15;
                if (to.equals("Fahrenheit")) return ((value - 273.15) * 1.8) + 32;
                break;
        }

        // Convert to target unit
        switch (to) {
            // cm to...
            case "Inch": return value / 2.54;
            case "Foot": return value / 30.48;
            case "Yard": return value / 91.44;
            case "Mile": return value / 160934;
            case "Kilometer": return value / 100000;

            // kg to...
            case "Pound": return value / 0.453592;
            case "Ounce": return value / 0.0283495;
            case "Ton": return value / 907.185;
            case "Gram": return value * 1000;
        }

        return value;
    }
}
