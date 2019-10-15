package com.example.oopproject.ManageProduct;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class ShowFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.view_list);
        recyclerView.setAdapter(new SimpleRecyclerViewListAdapter(dbManager.viewAllProducts()));
        return view;
    }
}
