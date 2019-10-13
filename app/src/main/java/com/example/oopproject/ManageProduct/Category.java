package com.example.oopproject.ManageProduct;

import com.example.oopproject.AppCompatProject;

import java.util.ArrayList;
import java.util.List;

public class Category extends AppCompatProject {
    List<String> categories;

    public Category() {
        this.categories = new ArrayList<String>();
    }

    public void addCategory(String newCategory) {
        categories.add(newCategory);
        dbManager.addCategory(newCategory);
    }

    public void removeCategory(String category) {
        categories.remove(category);
        dbManager.deleteCategory(category);
    }

    public void replaceCategory(String oldCategory, String newCategory) {
        categories.remove(oldCategory);
        categories.add(newCategory);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categories=" + categories.toString() +
                '}';
    }
}
