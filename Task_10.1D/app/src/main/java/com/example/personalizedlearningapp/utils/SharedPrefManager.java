package com.example.personalizedlearningapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "MyAppPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_IS_PREMIUM = "is_premium";

    private static SharedPrefManager instance;
    private final SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveUser(String username, String email, String phone, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void saveUserPremium(boolean isPremium) {
        sharedPreferences.edit().putBoolean(KEY_IS_PREMIUM, isPremium).apply();
    }

    public boolean isPremiumUser() {
        return sharedPreferences.getBoolean(KEY_IS_PREMIUM, false);
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getPhone() {
        return sharedPreferences.getString(KEY_PHONE, null);
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }

    public void logout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
