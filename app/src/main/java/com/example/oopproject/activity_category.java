package com.example.oopproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class activity_category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void AddNew(View view) {
        /* Si effettua una valutazione della funzione per capire se si sta eseguendo un nuovo ordine, registrando un nuovo prodotto, ecc...*/
        Bundle bundle = getIntent().getExtras();
        int funzione = bundle.getInt("view");
        Intent i = new Intent();
        switch (funzione) {
            case R.id.new_order:
                i = new Intent(this, ProductListActivity.class);
                break;
            case R.id.new_product:
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
