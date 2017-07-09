package com.wll.test.freemarker;

/**
 * Created by wll on 12/15/15.
 */
public class Animal {
    private String name;
    private String price;

    public Animal(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
