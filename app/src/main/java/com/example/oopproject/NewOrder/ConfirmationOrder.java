package com.example.oopproject.NewOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.R;

public class ConfirmationOrder extends AppCompatProjectOrder {

    View recyclerView;
    protected static RecyclerView.Adapter adapter;
    protected static TextView tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);
        tot = findViewById(R.id.total);
        tot.setText(order.total.toString() + " $");
        recyclerView = findViewById(R.id.confirmation_order);
        assert recyclerView != null;
        adapter = new SimpleAdapterConfirmation(order.list);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }


}
