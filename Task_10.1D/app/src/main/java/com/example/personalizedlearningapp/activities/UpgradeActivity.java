package com.example.personalizedlearningapp.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.utils.SharedPrefManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class UpgradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        findViewById(R.id.buttonStarter).setOnClickListener(v ->
                showGPaySheet("Starter Plan"));

        findViewById(R.id.buttonIntermediate).setOnClickListener(v ->
                showGPaySheet("Intermediate Plan"));

        findViewById(R.id.buttonAdvanced).setOnClickListener(v ->
                showGPaySheet("Advanced Plan"));
    }

    private void showGPaySheet(String planName) {
        View view = LayoutInflater.from(this).inflate(R.layout.sheet_gpay, null);
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);

        view.findViewById(R.id.gpayOption).setOnClickListener(v -> {
            dialog.dismiss();
            SharedPrefManager.getInstance(this).saveUserPremium(true);
            Toast.makeText(this, "✅ " + planName + " purchased!", Toast.LENGTH_LONG).show();
            finish();
        });

        dialog.show();
    }
}
