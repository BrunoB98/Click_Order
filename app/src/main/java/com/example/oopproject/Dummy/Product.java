package com.example.oopproject.Dummy;

import com.example.oopproject.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product implements Comparable<Product> {
    public Integer id;
    public String name;
    public Float price;
    public List<Ingredient> ingredients;

    public Product(Integer id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, float price, List<String> strings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = new ArrayList<Ingredient>();
        int position=0;
        for (String s : strings) {
            ingredients.add(new Ingredient(position, s));
            position++;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String printIngredients() {
        return "Ingredienti: " + ingredients;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}