package com.example.oopproject.Settings;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.example.oopproject.R;

public class HeaderFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.header_preferences, rootKey);
    }
}
