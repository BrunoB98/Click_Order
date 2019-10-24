package com.example.oopproject.ManageProduct;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import java.util.List;

import static com.example.oopproject.AppCompatProject.dbManager;

public class DeleteFragment extends Fragment {
    ImageButton btn_search;
    EditText search_prod;
    RecyclerView recyclerView;
    static List<Product> list;
    protected static RecyclerView.Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_delete, container, false);
       search_prod = view.findViewById(R.id.text_search);
       btn_search = view.findViewById(R.id.button_search);
       recyclerView = (RecyclerView) view.findViewById(R.id.all_products);

       btn_search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               list = dbManager.searchProduct(search_prod.getText().toString().toUpperCase());
               adapter = new SimpleRecyclerProductDelete(list);
               recyclerView.setAdapter(adapter);
           }
       });
       return view;
    }


}
