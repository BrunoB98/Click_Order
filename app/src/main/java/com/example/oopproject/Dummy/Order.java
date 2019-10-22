package com.example.oopproject.Dummy;

import com.example.oopproject.Dummy.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<Product> list;
    public Double total;

    public Order() {
        list = new ArrayList<Product>();
        total = 0.0;

    }

    public Order(List<Product> list) {
        this.list = list;
    }

    public StringBuilder PrintOrder() {
        StringBuilder sb = new StringBuilder();
        for(Product p : list) {
            sb.append(p.name + '\t' + p.price);
            sb.append((" "));
        }
        return sb;
    }

    public void add(Product product) {
        list.add(product);
        total += product.price;
    }

    public void remove(Product product) {
        list.remove(product);
        total -= product.price;
    }

    public void clear() {
        list.clear();
        total = 0.0;
    }

    public void printOrder() {
        System.out.println(list.toString());
        System.out.println(total.toString());
    }
}
