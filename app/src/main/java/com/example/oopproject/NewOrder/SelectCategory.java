package com.example.oopproject.NewOrder;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.oopproject.AppCompatProjectOrder;
import com.example.oopproject.MainActivity;
import com.example.oopproject.R;

public class SelectCategory extends AppCompatProjectOrder {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order.clear();
        setContentView(R.layout.activity_category);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        button = findViewById(R.id.antipasti);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.primi);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.secondi);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.pizze);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.dolci);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.bevande);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
    }

    public void SelectCategory(View view) {
        Button bt = (Button) view;
        /* Si effettua una valutazione della funzione per capire se si sta eseguendo un nuovo ordine, registrando un nuovo prodotto, ecc...*/
        Bundle bundle = getIntent().getExtras();
        int funzione = bundle.getInt("view");
        Intent i = new Intent();
        switch (funzione) {
            case R.id.new_order:
                i = new Intent(this, ProductListActivity.class);
                break;
            case R.id.manage_product:
                i = new Intent(this, MainActivity.class);
                break;
            case R.id.later_p:
                i = new Intent(this, activity_order.class);
                break;
            case R.id.delivery:
                i = new Intent(this, ProductListActivity.class);
                break;
            default:
                onStop();
        }
        /* Viene passata all'activity successiva la categoria di prodotto che si vuole aggiungere all'ordine o registrare*/
        System.out.println(bt.getText().toString());
        i.putExtra("categoria", bt.getText().toString());
        startActivity(i);
    }
}
