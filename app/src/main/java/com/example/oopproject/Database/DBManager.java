package com.example.oopproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.oopproject.Dummy.Order;
import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.List;

import static com.example.oopproject.AppCompatProject.ITEMS;
import static com.example.oopproject.AppCompatProject.ITEM_MAP;

public class DBManager {
    static final String PRODOTTO_IDP= "idp";
    static final String PRODOTTO_NOME = "nomep";
    static final String PRODOTTO_PREZZO = "prezzo";
    static final String PRODOTTO_NOMEC = "nomec";
    static final String CONTIENE_IDP = "idp";
    static final String CONTIENE_NOMEI = "nomei";
    static final String INGREDIENTE_NOMEI = "nomei";
    static final String DI_ID = "id";
    static final String DI_IDO = "ido";
    static final String DI_IDP = "idp";
    static final String ORDINE_IDO = "ido";
    static final String ORDINE_TAVOLO = "tavolo";
    static final String ORDINE_NPERSONE = "npersone";
    static final String ORDINE_TOTALE = "totale";
    static final String CATEGORIA_NOME = "nomec";
    static final String ORDINE_DATA = "datetime";
    static final String DBNAME= "TestDB";
    static final String ORDINE = "ordine";
    static final String PRODOTTO = "prodotto";
    static final String BUSINESS = "business";
    static final String CONTIENE = "contiene";
    static final String INGREDIENTE = "ingrediente";
    static final String DI = "di";
    static final String CATEGORIA = "categoria";

    Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    Cursor c;

    /* FUNZIONI DEL DB GENERALI */

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

    public void update() {
        ITEMS.clear();
        ITEM_MAP.clear();
        ITEMS = viewAllProducts();
        return;
    }

    /* FUNZIONI SUI PRODOTTI */

    public List<Product> viewProducts(String category) {
        List<Product> list = new ArrayList<Product>();
        String s = "SELECT * FROM prodotto WHERE prodotto.nomec = '" + category.toUpperCase() + "'";
        Cursor c = db.rawQuery(s, null);
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");
        if(c.moveToFirst()) {
            do {
                Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
                list.add(p);
             ITEM_MAP.put(p.id, p);
            } while (c.moveToNext());
        }
        return list;
    }

    public Product searchProductById(int id) {
        Cursor c  = db.rawQuery("SELECT * FROM prodotto WHERE idp = " + id, null);
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");
        c.moveToFirst();
        Product product = new Product(c.getInt(id), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
        return product;
    }

    public String getBusinessName() {
        String s;
        Cursor c  = db.rawQuery("SELECT * FROM business", null);
        int nomebIndex = c.getColumnIndex("nomeb");
        c.moveToFirst();
        s = c.getString(nomebIndex);
        return s;
    }

    public String getBusinessAddress() {
        String s;
        Cursor c  = db.rawQuery("SELECT * FROM business", null);
        int addressIndex = c.getColumnIndex("address");
        c.moveToFirst();
        s = c.getString(addressIndex);
        return s;
    }

    public void setBusiness(String name, String VATnumber, String address) {
        db.execSQL("DELETE FROM business");
        ContentValues cv = new ContentValues();
        cv.put("nomeb" , name.toUpperCase());
        cv.put("address", address.toUpperCase());
        cv.put("VATn", VATnumber);
        db.insert(BUSINESS, null, cv);
    }

    public List<Product> viewAllProducts() {
        List<Product> list = new ArrayList<Product>();
        Cursor c = db.rawQuery("SELECT * FROM prodotto ORDER BY nomep", null);
        int idIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int prezzoIndex = c.getColumnIndex("prezzo");
        int nomecIndex = c.getColumnIndex("nomec");
        while (c.moveToNext()) {
            Product p = new Product(c.getInt(idIndex), c.getString(nomepIndex), c.getFloat(prezzoIndex), c.getString(nomecIndex));
            list.add(p);
            ITEM_MAP.put(p.id, p);
        }
        return list;
    }

    public void add(String name, float price, String category) {
        ContentValues cv = new ContentValues();
        cv.put("nomep" , name.toUpperCase());
        cv.put("prezzo", price);
        cv.put("nomec", category.toUpperCase());
        db.insert(PRODOTTO, null, cv);
    }

    public void deleteProdCat(String categoryName) {
        db.delete(PRODOTTO, "prodotto.nomec ='" + categoryName.toUpperCase() + "'", null);
    }


    /* aggiunta in tabella contiene dato prodotto e ingrediente */
    public void addContiene(String productName, String ingredientName) {
        /* ottengo l'id del prodotto tramite una query */
        String sql = "SELECT * FROM prodotto WHERE prodotto.nomep = '" + productName.toUpperCase() + "'";
        Cursor c = db.rawQuery(sql, null);
        int idpIndex = c.getColumnIndex("idp");
        int nomepIndex = c.getColumnIndex("nomep");
        int id = 0;
        c.moveToFirst();
        id = c.getInt(idpIndex);
        System.out.println("id" + Integer.toString(c.getInt(idpIndex)));
        System.out.println("nome" + c.getString(nomepIndex));

        ContentValues cv = new ContentValues();
        cv.put(CONTIENE_NOMEI, ingredientName.toUpperCase());
        cv.put(CONTIENE_IDP, id);
        db.insert(CONTIENE, null, cv);
    }

    public List<Product> searchProduct(String name) {
        Cursor c  = db.rawQuery("SELECT * FROM prodotto WHERE prodotto.nomep LIKE '%" + name.toUpperCase() + "%'", null);
        int idIndex = c.getColumnIndex("idp");
        int nomeIndex = c.getColumnIndex("nomep");
        int prezzoindex = c.getColumnIndex("prezzo");
        int catIndex = c.getColumnIndex("nomec");
        List<Product> l = new ArrayList<Product>();
        if(c.moveToFirst()) {
            do {
                Product p = new Product(c.getInt(idIndex), c.getString(nomeIndex), c.getFloat(prezzoindex), c.getString(catIndex));
                l.add(p);
            } while (c.moveToNext());
        }
        return l;
    }

    public void viewContiene() {
        Cursor c = db.rawQuery("SELECT * FROM contiene", null);
        int idIndex = c.getColumnIndex("idp");
        int nomeIndex = c.getColumnIndex("nomei");
        if(c.moveToFirst()) {
            do {
                System.out.println("ingrediente: "+ c.getString(nomeIndex) + " prodotto: " + c.getInt(idIndex));
            }while(c.moveToNext());
        }
    }

    /*Ritorna un arraylist di stringhe contenenti i nomi degli ingredienti del prodotto passato come parametro*/
    public ArrayList<String> viewProductDetails(Product product) {
         Cursor c = db.rawQuery("SELECT nomei FROM contiene WHERE contiene.idp = " + product.getId(), null);
         int nomeiIndex = c.getColumnIndex("nomei");
         ArrayList<String> strings = new ArrayList<String>();
         if(c.moveToFirst()) {
             do {
                 strings.add(c.getString(nomeiIndex));
             } while(c.moveToNext());
         }
         return strings;
    }
    public ArrayList<String> viewProductDetails(int id) {
        Cursor c = db.rawQuery("SELECT nomei FROM contiene WHERE contiene.idp = " + id, null);
        int nomeiIndex = c.getColumnIndex("nomei");
        ArrayList<String> strings = new ArrayList<String>();
        if(c.moveToFirst()) {
            do {
                strings.add(c.getString(nomeiIndex));
            } while(c.moveToNext());
        }
        return strings;
    }

    public void deleteProduct(Product p) {
        db.delete(PRODOTTO, PRODOTTO_NOME + " = '" + p.getName().toUpperCase() + "'", null);
    }


    /* FUNZIONI ORDINE */

    public void addOrder(Order order) {
        ContentValues cv = new ContentValues();
        cv.put(ORDINE_TOTALE, order.total);
        cv.put(ORDINE_DATA, order.mydate);
        db.insert(ORDINE, null, cv);
    }

    public List<Order> viewAllOrder() {
        List<Order> orderList = new ArrayList<Order>();
        Cursor c = db.rawQuery("SELECT * FROM ordine ", null);
        int idoIndex = c.getColumnIndex("ido");
        int totIndex = c.getColumnIndex("totale");
        int dataIndex = c.getColumnIndex("datetime");
        Order o;
        if(c.moveToFirst()) {
            do {
                o = new Order(c.getInt(idoIndex), (double)c.getFloat(totIndex), c.getString(dataIndex));
                orderList.add(o);
            }while (c.moveToNext());
        }
        return orderList;
    }



    /* FUNZIONI INGREDIENTI */

    public List<String> viewAllIngredients() {
        Cursor c = db.rawQuery("SELECT * FROM ingrediente", null);
        int nomeIndex = c.getColumnIndex("nomei");
        List<String> l = new ArrayList<String>();
        if(c.moveToFirst()) {
            do {
                l.add(c.getString(nomeIndex));
            }while(c.moveToNext());
        }
        return l;
    }

    /* aggiunta ingrediente al database */
    public void addIngredient(String ingredientName) {
        ContentValues c = new ContentValues();
        c.put(INGREDIENTE_NOMEI, ingredientName.toUpperCase());
        db.insert(INGREDIENTE, null, c);

    }

    public void deleteIngredient(String ingredientName) {
        db.delete(INGREDIENTE,  INGREDIENTE_NOMEI + " = '"+ ingredientName.toUpperCase() +"'", null);
    }

    /* FUNZIONI CATEGORIA */

    public int categoryNumber() {
        Cursor c = db.rawQuery("SELECT * FROM categoria", null);
        return c.getCount();
    }

    public void addCategory(String categoryName) {
        ContentValues ccat = new ContentValues();
        ccat.put("nomec", categoryName.toUpperCase());
        db.insert(CATEGORIA, null, ccat );
    }

    public void deleteCategory(String categoryName) {
        db.delete(CATEGORIA, CATEGORIA_NOME + " = '" + categoryName.toUpperCase() + "'", null);
    }

    public List<String> viewCategory() {
        Cursor c = db.rawQuery("SELECT * FROM categoria", null);
        List<String> cat = new ArrayList<String>();
        int nomecIndex = c.getColumnIndex("nomec");
       if(c.moveToFirst()) {
           do {
               cat.add(c.getString(nomecIndex));
           } while (c.moveToNext());
       }
        return cat;
    }

    /* FUNZIONE PULIZIA DATABASE */
    public void deleteAllRecords() {
        db.execSQL("DELETE FROM di");
        db.execSQL("DELETE FROM contiene");
        db.execSQL("DELETE FROM ingrediente");
        db.execSQL("DELETE FROM prodotto");
        db.execSQL("DELETE FROM ordine");
        db.execSQL("DELETE FROM categoria");
    }
}
