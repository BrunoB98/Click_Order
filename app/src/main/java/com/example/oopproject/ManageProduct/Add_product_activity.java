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
    EditText name, price;
    List<EditText> ing = new ArrayList<EditText>();
    Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activity);
        name = (EditText) findViewById(R.id.prod_name);
        price = (EditText) findViewById(R.id.prod_price);
        category = (Spinner) findViewById(R.id.prod_cat);


        ing.add((EditText) findViewById(R.id.ing0));
        ing.add((EditText) findViewById(R.id.ing1));
        ing.add((EditText) findViewById(R.id.ing2));
        ing.add((EditText) findViewById(R.id.ing3));
        ing.add((EditText) findViewById(R.id.ing4));
        // Si imposta il menù a tendina con le categorie registrate nel database
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dbManager.viewCategory());
        category.setAdapter(adapter);
    }

    public void addToDatabase(View view) {
        dbManager.add(name.getText().toString(), Float.parseFloat(price.getText().toString()), category.getSelectedItem().toString());
        dbManager.update();
        for(int i = 0; i<ing.size(); i++) {
            if (ing.get(i).getText().toString() != "") {
                dbManager.addIngredient(ing.get(i).getText().toString());
                dbManager.addContiene(name.getText().toString(), ing.get(i).getText().toString());
            }
        }
        Toast.makeText(getApplicationContext(), "Product added to database", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ManageProducts.class));
    }
}
