package com.example.oopproject.Dummy;

import java.util.ArrayList;
import java.util.List;

import static com.example.oopproject.AppCompatProject.dbManager;

public class Product implements Comparable<Product> {
    public Integer id;
    public String name;
    public Float price;
    public String category;
    public List<String> ingredients;
    public boolean[] checked;

    public Product(Integer id, String name, Float price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.ingredients = new ArrayList<String>(dbManager.viewProductDetails(id));
        this.checked = new boolean[ingredients.size()];
        for (int i = 0; i<ingredients.size(); i++) {
            checked[i] = true;
        }
    }

    public Product(Product p) {
        this.id = p.id;
        this.name = p.name;
        this.price = p.price;
        this.category = p.category;
        this.ingredients = new ArrayList<String>(dbManager.viewProductDetails(id));
        this.checked = new boolean[ingredients.size()];
        for (int i = 0; i<ingredients.size(); i++) {
            this.checked[i] = p.checked[i];
        }
    }

    /* setta gli ingredienti sel prodotto ma non ha influenza sul database cosa necessaria per modifiche dei prodotto relative ad uno
    specifico ordine*/
    public void setProductIngredients (List<String> ingreds) {
        for (String s : ingreds) {
            if(!this.ingredients.contains(s)) {
                checked[this.ingredients.indexOf(s)] = false;
            }
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
                ", checked=" + Sta() +
                '}';
    }

    public void removeIngredient(String ingredientName) {
        for(int i = 0; i < ingredients.size(); i++) {
            if(this.ingredients.get(i).equals(ingredientName)) {
                this.ingredients.remove(i);
            }
        }
    }

    public String Sta() {
        String s = "";
        for(int i=0;i<checked.length;i++) {
            if(checked[i]==true) {
                s += 1;
            }
            else {
                s += 0;
            }
        }
        return s;
    }

    @Override
    public int compareTo(Product product) {
        return this.name.compareTo(product.name);
    }
}