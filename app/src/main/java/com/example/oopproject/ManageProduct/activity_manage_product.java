package com.example.oopproject.ManageProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.example.oopproject.R;

public class activity_manage_product extends AppCompatActivity {
    public FragmentManager fm = getSupportFragmentManager();
    public Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

    }

    public void setFragment(View view) {
       // fragment = new AddFragment();

        switch(view.getId()) {
            case R.id.Add_button:
                fragment = new AddFragment();
                fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
                case R.id.Modify_button:
                fragment = new ModifyFragment();
                fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
           /* case R.id.Delete_button:
                fragment = new DeleteFragment();
                break;
            case R.id.View_button:
                fragment = new ViewFragment();
                break;*/
            default:
                fragment = new Fragment();
                fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
                break;
        }
      //  fm.beginTransaction().replace(R.id.Frag_panel, fragment).commit();
    }
}
