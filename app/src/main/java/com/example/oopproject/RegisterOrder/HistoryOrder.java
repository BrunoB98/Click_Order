package com.example.oopproject.RegisterOrder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oopproject.AppCompatProject;
import com.example.oopproject.NewOrder.SimpleAdapterConfirmation;
import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class HistoryOrder extends AppCompatProject {
    protected static RecyclerView.Adapter adapter;
    View rw_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);
        rw_history = findViewById(R.id.rw_history_order);
        assert rw_history != null;


        adapter = new SimpleAdapterHistoryOrder(dbManager.viewAllOrder());
        setupRecyclerView((RecyclerView) rw_history);



        System.out.println("Ciao sono l'activity Hisotry Order");
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

}
