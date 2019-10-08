package com.example.oopproject.Dummy;

import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product> {
    public Integer id;
    public String name;
    public Float price;
    public String category;
    public List<Ingredient> ingredients;

    public Product(Integer id, String name, Float price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Product(int id, String name, float price, String category, List<String> strings) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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
                ", category=" + category +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}