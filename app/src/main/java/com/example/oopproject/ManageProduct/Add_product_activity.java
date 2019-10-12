package com.example.oopproject.ManageProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.oopproject.ManageProduct.activity_manage_product;
import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class Add_product_activity extends AppCompatActivity {
    EditText name, price, ing0, ing1, ing2, ing3, ing4;
    Spinner category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activity);
        name = (EditText) findViewById(R.id.prod_name);
        price = (EditText) findViewById(R.id.prod_price);
        category = (Spinner) findViewById(R.id.prod_cat);
        String[] items = new String[]{"antipasti", "primi", "secondi", "pizze", "dolci", "bevande"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        category.setAdapter(adapter);
    }

    public void addToDatabase(View view) {
        dbManager.add(name.getText().toString(), Float.parseFloat(price.getText().toString()), category.getSelectedItem().toString());
        dbManager.update();
        Toast.makeText(getApplicationContext(), "Product added to database", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, activity_manage_product.class));
    }
}
