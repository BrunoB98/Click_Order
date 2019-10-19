package com.example.oopproject.Settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.oopproject.R;

public class BusinessFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.business_preferences, rootKey);
    }
}
