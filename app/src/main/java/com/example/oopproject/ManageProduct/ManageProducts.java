package com.example.oopproject.ManageProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

public class ManageProducts extends AppCompatActivity {
    public FragmentManager fm = getSupportFragmentManager();
    public Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

    }

    @Override
    public void onBackPressed () {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void setFragment(View view) {
        // fragment = new AddFragment();

        switch (view.getId()) {
            case R.id.Add_button:
                startActivity(new Intent(this, Add_product_activity.class));
                break;
            case R.id.Delete_button:
                fragment = new DeleteFragment();
                fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
            case R.id.View_button:
                fragment = new ShowFragment();
                fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
            default:
                break;
        }

    }



}
