package com.example.oopproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static com.example.oopproject.AppCompatProject.dbManager;


public class AddCategoryFragment extends Fragment {


    EditText category_name;
    Button add_category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        category_name = view.findViewById(R.id.category_name);
        add_category = view.findViewById(R.id.add_category_button);
        add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.addCategory(category_name.getText().toString());
                category_name.setText("");
            }
        });
        return view;
    }

}
