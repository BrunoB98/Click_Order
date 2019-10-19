package com.example.oopproject.Settings;

import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.oopproject.R;

public class LanguageFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.language_preferences, rootKey);
        ListPreference listPreference = new ListPreference(getContext());
    }
}
