package com.example.oopproject.ManageProduct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oopproject.ManageProduct.activity_manage_product;
import com.example.oopproject.R;

import static com.example.oopproject.AppCompatProject.dbManager;

public class Add_product_activity extends AppCompatActivity {
    EditText name, price, category, ing0, ing1, ing2, ing3, ing4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_activity);
        name = (EditText) findViewById(R.id.prod_name);
        price = (EditText) findViewById(R.id.prod_price);
        category = (EditText) findViewById(R.id.prod_cat);
    }

    public void addToDatabase(View view) {
        dbManager.add(name.getText().toString(), Float.parseFloat(price.getText().toString()), category.getText().toString());
        dbManager.update();
        Toast.makeText(getApplicationContext(), "Product added to database", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, activity_manage_product.class));
    }
}
