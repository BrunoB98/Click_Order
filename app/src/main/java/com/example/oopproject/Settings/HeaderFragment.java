package com.example.oopproject.Settings;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class HeaderFragment extends PreferenceFragmentCompat {

    AlertDialog.Builder alertDialog;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.header_preferences, rootKey);


        Preference p = findPreference("reset_header");
        p.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle(R.string.attention);
                alertDialog.setMessage(R.string.alert_reset);
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), R.string.delete_all, Toast.LENGTH_SHORT).show();
                        dbManager.deleteAllRecords();
                    }
                });
                alertDialog.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });
                alertDialog.show();
                return true;
            }
        });
    }
}
