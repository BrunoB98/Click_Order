package com.example.oopproject.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.oopproject.AppCompatProject;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;
import com.example.oopproject.Settings.HeaderFragment;

public class SettingsActivity extends AppCompatProject implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private static final String TITLE_TAG = "settingsActivityTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.settings, new HeaderFragment()).commit();
        } else {
            setTitle(savedInstanceState.getCharSequence(TITLE_TAG));
        }
        getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                            setTitle(R.string.title_activity_settings);
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            String name = sharedPreferences.getString("business_name", "business name");
                            String VATnumber = sharedPreferences.getString("business_PIVA", "VAT numeber");
                            String address = sharedPreferences.getString("business_address", "address");
                            dbManager.setBusiness(name, VATnumber, address);
                        }
                    }
                });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String name = sharedPreferences.getString("business_name", "business name");
        String VATnumber = sharedPreferences.getString("business_PIVA", "VAT numeber");
        String address = sharedPreferences.getString("business_address", "address");
        dbManager.setBusiness(name, VATnumber, address);
        startActivity(getParentActivityIntent());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, getTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            return true;
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {

        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = getSupportFragmentManager().getFragmentFactory().instantiate(getClassLoader(), pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.settings, fragment).addToBackStack(null).commit();
        setTitle(pref.getTitle());
        return true;
    }
}
