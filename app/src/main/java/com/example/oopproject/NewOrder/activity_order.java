package com.example.oopproject.NewOrder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class activity_order extends AppCompatProjectOrder {

    View recyclerView;
    DecimalFormat df = new DecimalFormat("##.##");
    protected static Adapter adapter;
    protected static TextView tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tot = findViewById(R.id.total);
        tot.setText(df.format(order.total)+ " €");
        recyclerView = findViewById(R.id.summary_order);
        assert recyclerView != null;
        adapter = new SimpleItemRecyclerViewAdapter2(order.list);
        setupRecyclerView((RecyclerView) recyclerView);
        order.printOrder();
    }


    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    public void goToConfirmation (View view) {
        startActivity(new Intent(this, ConfirmationOrder.class));
    }

}
