package com.example.oopproject.ManageProduct;

import android.widget.Toast;

import com.example.oopproject.AppCompatProject;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatProject {
    List<String> categories;

    public Category() {
        this.categories = new ArrayList<String>();
    }

    public void addCategory(String newCategory) {
        if(categories.contains(newCategory.toLowerCase())) {
         //   Toast.makeText(getApplicationContext(), "Existing category", Toast.LENGTH_SHORT).show();
        }
        else {
            categories.add(newCategory.toLowerCase());
            dbManager.addCategory(newCategory.toLowerCase());
          //  Toast.makeText(getApplicationContext(), "Category successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeCategory(String category) {
        if(categories.contains(category.toLowerCase())) {
            categories.remove(category.toLowerCase());
            dbManager.deleteCategory(category.toLowerCase());
          //  Toast.makeText(getApplicationContext(), "Category successfully removed", Toast.LENGTH_SHORT).show();
        }
        else {
          //  Toast.makeText(getApplicationContext(), "Category not exists", Toast.LENGTH_SHORT).show();
        }
    }

    public void replaceCategory(String oldCategory, String newCategory) {
        this.removeCategory(oldCategory);
        this.addCategory(newCategory);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categories=" + categories.toString() +
                '}';
    }
}
