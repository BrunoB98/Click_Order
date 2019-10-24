package com.example.oopproject.Dummy;

import java.util.ArrayList;
import java.util.List;

public class Product implements Comparable<Product> {
    public Integer id;
    public String name;
    public Float price;
    public String category;
    public List<String> ingredients;

    public Product(Integer id, String name, Float price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ingredients = new ArrayList<String>();
    }


    /* setta gli ingredienti sel prodotto ma non ha influenza sul database cosa necessaria per modifiche dei prodotto relative ad uno
    specifico ordine*/
    public void setProductIngredients (List<String> ingreds) {
        ingredients.clear();
        for (String s : ingreds) {
            this.ingredients.add(s);
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

    public String getCategory() { return category; }


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

    public void removeIngredient(String ingredientName) {
        for(int i = 0; i < ingredients.size(); i++) {
            if(this.ingredients.get(i).equals(ingredientName)) {
                this.ingredients.remove(i);
            }
        }
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}