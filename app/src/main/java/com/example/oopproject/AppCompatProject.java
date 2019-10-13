package com.example.oopproject;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.oopproject.Database.DBManager;
import com.example.oopproject.Dummy.Product;
import com.example.oopproject.ManageProduct.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AppCompatProject extends AppCompatActivity {
    public static DBManager dbManager;
    public static List<Product> ITEMS = new ArrayList<Product>();
    public static Map<Integer, Product> ITEM_MAP = new HashMap<Integer, Product>();
    public static int COUNT = 0;
    public static Category categories = new Category();

}

