package com.example.oopproject.ManageCateg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;


public class ViewCategoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_category, container, false);
        String string = "Categoria presenti e numero di prodotti presenti: \n\n";
        for(String s : dbManager.viewCategory()) {
            string += s + "\r (" + dbManager.viewProducts(s).size() + ")\n\n";
        }
        TextView tx = view.findViewById(R.id.category_view);
        tx.setText(string);
        return view;
    }

}
