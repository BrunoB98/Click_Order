package com.example.oopproject.NewOrder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class ConfirmationOrder extends AppCompatProjectOrder {

    View recyclerView;
    protected static RecyclerView.Adapter adapter;
    protected static TextView tot;
    DecimalFormat df = new DecimalFormat("##.##");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);
        tot = findViewById(R.id.total);
        tot.setText(df.format(order.total) + " €");
        recyclerView = findViewById(R.id.confirmation_order);
        assert recyclerView != null;
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
                order.mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                dbManager.addOrder(order);
                order.clear();
                Toast.makeText(getApplicationContext(), R.string.order_registered, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        alertDialog.setNegativeButton(R.string.cancel_order, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                order.clear();
                Toast.makeText(getApplicationContext(), R.string.order_canceled, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        alertDialog.setNeutralButton(R.string.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.show();
    }
}
