package com.example.oopproject.ManageProduct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oopproject.AppCompatProject;
import com.example.oopproject.R;

import java.util.ArrayList;
import java.util.List;

public class Add_product_activity extends AppCompatProject {
    EditText name, price, ing0, ing1, ing2, ing3, ing4;
    Spinner category;
    List<String> ingred = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activity);
        name = (EditText) findViewById(R.id.prod_name);
        price = (EditText) findViewById(R.id.prod_price);
        category = (Spinner) findViewById(R.id.prod_cat);
        ing0 = (EditText) findViewById(R.id.ing0);
        ing1 = (EditText) findViewById(R.id.ing1);
        ing2 = (EditText) findViewById(R.id.ing2);
        ing3 = (EditText) findViewById(R.id.ing3);
        ing4 = (EditText) findViewById(R.id.ing4);
        // Si imposta il menù a tendina con le categorie registrate nel database
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        category.setAdapter(adapter);
    }

    public void addToDatabase(View view) {
        dbManager.add(name.getText().toString(), Float.parseFloat(price.getText().toString()), category.getSelectedItem().toString());
        dbManager.update();
        Toast.makeText(getApplicationContext(), "Product added to database", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ManageProducts.class));
    }
}
