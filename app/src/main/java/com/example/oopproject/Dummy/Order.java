package com.example.oopproject.Dummy;

import com.example.oopproject.Dummy.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    public int id;
    public String mydate;
    public List<Product> list;
    public Double total;

    public Order() {
        list = new ArrayList<Product>();
        total = 0.0;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMydate() {
        return mydate;
    }

    public void setMydate(String mydate) {
        this.mydate = mydate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Order(int id, Double total, String mydate) {
        this.id = id;
        this.total = total;
        this.mydate = mydate;
        list = new ArrayList<Product>();

    }


    public Order(List<Product> list) {
        this.list = new ArrayList<>(list);
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

    public String toString() {
        String s = "Ordine: " + Integer.toString(this.id) + " data: " + this.mydate + " prod: " + this.list.toString() + " tot:" + this.total;
        return s;
    }

    public void printOrder() {
        System.out.println(list.toString());
        System.out.println(total.toString());
    }
}
