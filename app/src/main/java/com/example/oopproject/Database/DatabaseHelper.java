package com.example.oopproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

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
    static final String CATEGORIA_NOME = "nomec";

    static final String DBNAME= "TestDB";
    static final String ORDINE = "ordine";
    static final String PRODOTTO = "prodotto";
    static final String CONTIENE = "contiene";
    static final String INGREDIENTE = "ingrediente";
    static final String DI = "di";
    static final String CATEGORIA = "categoria";

    static final int DATABASE_VERSIONE = 1;

    static final String DB_ORDINE = " CREATE TABLE IF NOT EXISTS ordine ( ido integer primary key autoincrement, totale float);" ;
    static final String DB_INGREDIENTE = " CREATE TABLE IF NOT EXISTS ingrediente (nomei varchar(50) primary key);";
    static final String DB_PRODOTTO = "CREATE TABLE IF NOT EXISTS prodotto (idp integer primary key autoincrement, nomep varchar(50) unique, prezzo float not null, nomec varchar(50), foreign key (nomec) references categoria);";
    static final String DB_CONTIENE = "CREATE TABLE IF NOT EXISTS contiene (nomei varchar(50), idp integer, foreign key (nomei) references ingrediente, foreign key (idp) references prodotto, primary key (nomei, idp));";
    static final String DB_DI = "CREATE TABLE IF NOT EXISTS di (id integer primary key autoincrement, ido integer, idp integer, foreign key (ido) references ordine, foreign key (idp) references prodotto );";
    static final String DB_CATEGORIA = "CREATE TABLE IF NOT EXISTS categoria (nomec varchar(50) primary key );";


    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DATABASE_VERSIONE);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DB_INGREDIENTE);
            db.execSQL(DB_CATEGORIA);
            db.execSQL(DB_CONTIENE);
            db.execSQL(DB_ORDINE);
            db.execSQL(DB_PRODOTTO);
            db.execSQL(DB_DI);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),"Aggiornamento database dalla versione " + oldVersion + " alla "
                + newVersion + ". I dati esistenti verranno eliminati.");
        db.execSQL("DROP TABLE IF EXISTS contiene");
        db.execSQL("DROP TABLE IF EXISTS di");
        db.execSQL("DROP TABLE IF EXISTS prodotto");
        db.execSQL("DROP TABLE IF EXISTS ordine");
        db.execSQL("DROP TABLE IF EXISTS ingrediente");
        db.execSQL("DROP TABLE IF EXISTS categoria");


        onCreate(db);
    }
}
