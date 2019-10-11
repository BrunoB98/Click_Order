package com.example.oopproject.Database;

import android.content.Context;
import android.database.Cursor;

import com.example.oopproject.Database.DBManager;
import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListProductDb {
  /*  public List<Product> ITEMS = new ArrayList<Product>();

    public static Map<Integer, Product> ITEM_MAP = new HashMap<Integer, Product>();

    public int COUNT = 0;

    DBManager dbManager;

    public ListProductDb (Context context) {
        dbManager = new DBManager(context);
        dbManager.open();
       dbManager.deleteAllRecords();
        dbManager.add("piselli", (float) 7.3, "primi");
        dbManager.add("bistecca", (float) 7.3, "secondi");
        dbManager.add("ceci", (float) 7.3, "secondi");
        Cursor c = dbManager.viewAllProducts();
        COUNT = c.getCount();
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");

        c.moveToFirst();

        do {
            System.out.println(Integer.toString(c.getInt(idIndex)) + c.getString(nomepIndex) + Float.toString(c.getFloat(prezzoIndex)));
            Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
            ITEMS.add(p);
            ITEM_MAP.put(p.id, p);
        } while (c.moveToNext());
        dbManager.close();
    }*/

}
