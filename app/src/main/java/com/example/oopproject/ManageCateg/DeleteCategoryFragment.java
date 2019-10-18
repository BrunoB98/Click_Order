package com.example.oopproject.ManageCateg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;


public class DeleteCategoryFragment extends Fragment {

    Button delete_category;
    TextView category_sel;
    Spinner categories;
    ArrayAdapter<String> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_category, container, false);

        delete_category = view.findViewById(R.id.delete_category_button);
        //delete_category.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float)0.2);
        category_sel = view.findViewById(R.id.category_selection);
        //category_sel.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) 0.2);
        categories = view.findViewById(R.id.prod_cat);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        categories.setAdapter(adapter);
        delete_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbManager.deleteCategory(categories.getSelectedItem().toString());
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
                categories.setAdapter(adapter);
            }
        });
        return view;
    }



    /*
    public void deleteCategoryFromDB() {
        dbManager.deleteCategory(category_name.getText().toString());
        System.out.println("Eliminata categoria " + category_name.getText().toString());

    }*/


}
