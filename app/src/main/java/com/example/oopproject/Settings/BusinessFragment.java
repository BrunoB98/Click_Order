package com.example.oopproject.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.oopproject.AppCompatProject;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class BusinessFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.business_preferences, rootKey);
    }
}
