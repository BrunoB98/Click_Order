package com.example.oopproject.Dummy;

import com.example.oopproject.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product implements Comparable<Product> {
    public final Integer id;
    public final String name;
    public float price;
   // public final List<Ingredient> = new ArrayList<Ingredient>();

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
          // for (String s : ingre) {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}