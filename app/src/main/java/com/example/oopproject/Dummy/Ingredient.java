package com.example.oopproject.Dummy;

public class Ingredient implements Comparable<Ingredient> {
        public int id;
        public String name;

    public Ingredient(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public int compareTo(Ingredient ingredient) {
        return this.name.compareTo(ingredient.name);
    }
}
