package com.example.oopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.List;

public class activity_order extends AppCompatProjectOrder {

    View recyclerView;
    protected static Adapter adapter;
    protected static TextView tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tot = findViewById(R.id.total);
        tot.setText(order.total.toString() + " $");
        recyclerView = findViewById(R.id.summary_order);
        assert recyclerView != null;
        adapter = new SimpleItemRecyclerViewAdapter2(order.list);
        setupRecyclerView((RecyclerView) recyclerView);
    }


    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }


}
