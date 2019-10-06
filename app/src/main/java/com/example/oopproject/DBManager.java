package com.example.oopproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DBManager {
    static final String PRODOTTO_IDP= "idp";
    static final String PRODOTTO_N = "nomep";
    static final String PRODOTTO_PRE = "prezzo";
    static final String PRODOTTO_NOMEC = "nomec";
    static final String CONTIENE_IDP = "idp";
    static final String CONTIENE_IDI = "idi";
    static final String INGREDIENTE_IDI = "idi";
    static final String INGREDIENTE_NOMEI = "nomei";
    static final String DI_IDO = "ido";
    static final String DI_IDP = "idp";
    static final String ORDINE_IDO = "ido";
    static final String ORDINE_TAVOLO = "tavolo";
    static final String ORDINE_NPERSONE = "npersone";
    static final String ORDINE_TOTALE = "totale";
    static final String DBNAME= "TestDB";
    static final String ORDINE = "ordine";
    static final String PRODOTTO = "prodotto";
    static final String CONTIENE = "contiene";
    static final String INGREDIENTE = "ingrediente";
    static final String DI = "di";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor c ;


    public DBManager(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);

    }



    public Cursor viewProducts(String category) {
        String s = "SELECT * FROM prodotto WHERE prodotto.nomec = " + category;
        Cursor c = db.rawQuery(s, null);
        return c;
    }

    public Cursor viewAllProducts() {
        Cursor c = db.rawQuery("SELECT * FROM prodotto ORDER BY nomep", null);
        return c;
    }

    public void addProduct(String productName, float price, String categoryName, ArrayList<String> ingredients) {
        ContentValues cv = new ContentValues();
        cv.put("nomep" , productName);
        cv.put("prezzo", price);
        cv.put("nomec", categoryName);
        db.insert(PRODOTTO, null, cv);
        Cursor c_idp = db.rawQuery("SELECT idp FROM prodotto WHERE nomep = " + productName, null);
        int idIndex = c_idp.getColumnIndex("idp");
        int id = c_idp.getInt(idIndex);
        int i;
        ContentValues c_cont = new ContentValues();
        c_cont.put("idp", id);
        for (i = 0; i < ingredients.size(); i++) {
            Cursor ci = db.rawQuery("SELECT idp FROM ingrediente WHERE nomei = " + ingredients.get(i), null);
            int idiIndex = ci.getColumnIndex("idi");
            int id_ing = ci.getInt(idiIndex);
            c_cont.put("idi", id_ing );
            db.insert(CONTIENE, null, c_cont );
        }

    }

    /*Ritorna un arraylist di stringhe contenenti i nomi degli ingredienti del prodotto passato come parametro*/
    public ArrayList<String> viewProductDetails( String productName) {

         Cursor c = db.rawQuery("SELECT nomei FROM prodotto INNER JOIN contiene INNER JOIN ingrediente WHERE prodotto.nomep = ' " + productName + "'", null);
         int nomeiIndex = c.getColumnIndex("nomei");
         ArrayList<String> strings = new ArrayList<String>();
         if(c.moveToFirst()) {
             do {
                 strings.add(c.getString(nomeiIndex));
             } while(c.moveToNext());
         }
         return strings;
    }

    public void addToOrder(int productId, int orderId, int tableNumber, int personNumber ) {

        ContentValues c_di = new ContentValues();
        ContentValues c_ordine = new ContentValues();
        c_di.put(DI_IDP, productId);
        c_di.put(DI_IDO, orderId);

        c_ordine.put(ORDINE_IDO, orderId);
        c_ordine.put(ORDINE_TAVOLO, tableNumber);
        c_ordine.put(ORDINE_NPERSONE, personNumber);



        db.insert(DI, null, c_di);
        db.insert(ORDINE, null, c_ordine);
    }

    public void addIngredient(String ingredientName) {

        ContentValues c = new ContentValues();
        c.put(INGREDIENTE_NOMEI, ingredientName);
        db.insert(INGREDIENTE, null, c);

    }

    public void deleteProduct(int productId, String productName) {
        db.delete(PRODOTTO, "nomep = '" + productName + "'", null);
    }

    public void deleteIngredient(int ingredientId, String ingredientName) {
        db.delete(INGREDIENTE, "nomei = '"+ ingredientName +"'", null);
    }


}
