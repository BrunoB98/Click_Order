package com.example.oopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.oopproject.Database.DBManager;
import com.example.oopproject.Dummy.DummyContent;
import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogActivity extends AppCompatActivity {

   // public static DummyContent cont;
   public static List<Product> ITEMS = new ArrayList<Product>();

    /**
     * A map of sample product, by ID.
     */
    public static final Map<Integer, Product> ITEM_MAP = new HashMap<Integer, Product>();

    public static int COUNT = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        DBManager dbManager = new DBManager(this);
        dbManager.open();
        dbManager.deleteAllRecords();
        dbManager.add("piselli", (float) 7.3, "primi");
        dbManager.add("cazzi", (float) 7.3, "secondi");
        Cursor c = dbManager.viewAllProducts();
        COUNT = c.getCount();
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");

        c.moveToFirst();
        // Add some sample items.
        do {
            System.out.println(Integer.toString(c.getInt(idIndex))+ c.getString(nomepIndex) + Float.toString(c.getFloat(prezzoIndex)));
            Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
            ITEMS.add(p);
            ITEM_MAP.put(p.id, p);
        } while (c.moveToNext());
        dbManager.close();
       /*
    public void update() {
        ITEMS.clear();
        ITEM_MAP.clear();
        do {
            Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
            ITEMS.add(p);
            ITEM_MAP.put(p.id, p);
        } while(c.moveToNext());

    }*/
        startActivity(new Intent(this, MainActivity.class));
    }
}

