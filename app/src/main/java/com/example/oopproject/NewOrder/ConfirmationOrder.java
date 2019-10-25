package com.example.oopproject.NewOrder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        order.printOrder();
        adapter = new SimpleAdapterConfirmation(order.list);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    /* Funzione per settare un nuovo Adapter utile per visualizzare gli elementi */
    public void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    public void completeOrder(View view) {

        AlertDialog.Builder alertDialog;
        alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.attention);
        alertDialog.setMessage(R.string.complete_order);
        alertDialog.setIcon(android.R.drawable.ic_popup_reminder);
        alertDialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbManager.addOrder(order);
            }
        });
        alertDialog.setNegativeButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        alertDialog.show();




    }


}
