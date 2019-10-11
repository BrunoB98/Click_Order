package com.example.oopproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.oopproject.Database.DatabaseHelper;
import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.oopproject.AppCompatProject.ITEMS;
import static com.example.oopproject.AppCompatProject.ITEM_MAP;

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

    Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor c;

    public DBManager(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(context);

    }

    public DBManager open() throws SQLException {
        db = DBHelper.getReadableDatabase();
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public List<Product> init() {
        this.deleteAllRecords();
        System.out.println("ho cancellato i record");
        this.add("piselli", (float) 7.3, "primi");
        this.add("bistecca", (float) 7.3, "secondi");
        this.add("ceci", (float) 7.3, "secondi");
        System.out.println("ho aggiunto i prodotti");
        c = this.viewAllProducts();
        System.out.println("ho visualizzato i prodotti");
       // COUNT = c.getCount();
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");

        c.moveToFirst();
        // Add some sample items.
        do {
            Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
            ITEMS.add(p);
            ITEM_MAP.put(p.id, p);
        } while (c.moveToNext());
        return ITEMS;
    }

    public Cursor viewProducts(String category) {
        String s = "SELECT * FROM prodotto WHERE prodotto.nomec = " + category;
        Cursor c = db.rawQuery(s, null);
        return c;
    }

    public Cursor viewIngredients() {
        Cursor c = db.rawQuery("SELECT * FROM ingrediente", null);
        return c;
    }

    public Cursor viewAllProducts() {
        Cursor c = db.rawQuery("SELECT * FROM prodotto ORDER BY nomep", null);
        return c;
    }

    public void add(String name, float price, String category) {
        ContentValues cv = new ContentValues();
        cv.put("nomep" , name);
        cv.put("prezzo", price);
        cv.put("nomec", category);
        db.insert(PRODOTTO, null, cv);
    }

    public void addProduct(String productName, float price, String categoryName) {
        ContentValues cv = new ContentValues();
        cv.put("nomep" , productName);
        cv.put("prezzo", price);
        cv.put("nomec", categoryName);
        db.insert(PRODOTTO, null, cv);
        /*
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
        */

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

    public void deleteAllRecords() {
        db.execSQL("DELETE FROM di");
        db.execSQL("DELETE FROM contiene");
        db.execSQL("DELETE FROM ingrediente");
        db.execSQL("DELETE FROM prodotto");
        db.execSQL("DELETE FROM ordine");
    }


}
