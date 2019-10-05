package com.example.oopproject;

import com.example.oopproject.Dummy.Product;

import java.util.List;

public class Order {
    public List<Product> order;

    public Order(List<Product> order) {
        this.order = order;
    }

    public void PrintOrder(Order o) {
    }

    public void add(Product product) {
        order.add(product);
    }
}
