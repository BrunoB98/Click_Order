package com.example.oopproject.ManageProduct;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.SearchView;

import com.example.oopproject.Dummy.Product;
import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class DeleteFragment extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
      //  SearchView searchView = (SearchView) view.findViewById(R.id.search_bar);
       /* searchView.setSuggestionsAdapter();
        searchView.getSuggestionsAdapter()*/
       return view;
    }
}
