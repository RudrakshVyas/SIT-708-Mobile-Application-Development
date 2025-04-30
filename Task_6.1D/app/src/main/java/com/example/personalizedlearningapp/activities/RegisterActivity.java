package com.example.personalizedlearningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalizedlearningapp.R;
import com.example.personalizedlearningapp.utils.SharedPrefManager;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextEmail, editTextConfirmEmail, editTextPassword, editTextConfirmPassword, editTextPhone;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextConfirmEmail = findViewById(R.id.editTextConfirmEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(view -> registerUser());
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String confirmEmail = editTextConfirmEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(confirmEmail) ||
                TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.equals(confirmEmail)) {
            Toast.makeText(this, "Emails do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // It Saves user locally
        SharedPrefManager.getInstance(this).saveUser(username, email, phone, password);
        Toast.makeText(this, "Registration successful! Please login.", Toast.LENGTH_SHORT).show();

        // It Goes to select interest page
        Intent intent = new Intent(RegisterActivity.this, InterestsActivity.class);
        startActivity(intent);
        finish();

    }
}
