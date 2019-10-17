package com.example.oopproject.ManageCateg;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.oopproject.AppCompatProject;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

public class ManageCategories extends AppCompatProject {
    public FragmentManager fm = getSupportFragmentManager();
    public Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);

    }

    @Override
    public void onBackPressed () {
        startActivity(new Intent(this, MainActivity.class));
    }


    public void setFragment(View view) {
        // fragment = new AddFragment();

        switch(view.getId()) {
            case R.id.add_category_button:
                fragment = new AddCategoryFragment();
                fm.beginTransaction().replace(R.id.frag_category_panel, fragment).commit();
                break;
            case R.id.delete_category_button:
                fragment = new DeleteCategoryFragment();
                fm.beginTransaction().replace(R.id.frag_category_panel, fragment).commit();
                break;
            case R.id.view_category_button:
                fragment = new ViewCategoryFragment();
                fm.beginTransaction().replace(R.id.frag_category_panel, fragment).commit();
                break;
            default:
                fragment = new Fragment();
                // fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
        }
        // fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
    }


}
