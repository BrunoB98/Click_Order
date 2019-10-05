package com.example.oopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class activity_category extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        button = findViewById(R.id.cat);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.cat1);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.cat2);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.cat3);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.cat4);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
        button = findViewById(R.id.cat5);
        button.setTextSize((float) (dm.widthPixels*0.06/dm.density));
    }

    public void SelectCategory(View view) {
        /* Si effettua una valutazione della funzione per capire se si sta eseguendo un nuovo ordine, registrando un nuovo prodotto, ecc...*/
        Bundle bundle = getIntent().getExtras();
        int funzione = bundle.getInt("view");
        Intent i = new Intent();
        switch (funzione) {
            case R.id.new_order:
                i = new Intent(this, ProductListActivity.class);
                break;
            case R.id.manage_product:
                i = new Intent(this, ProductListActivity.class);
                break;
            case R.id.later_p:
                i = new Intent(this, ProductListActivity.class);
                break;
            case R.id.delivery:
                i = new Intent(this, ProductListActivity.class);
                break;
            default:
                onStop();
        }
        /* Viene passata all'activity successiva la categoria di prodotto che si vuole aggiungere all'ordine o registrare*/
        i.putExtra("categoria", view.getId());
        startActivity(i);
    }
}
