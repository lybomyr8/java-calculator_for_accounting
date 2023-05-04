package com.joreijarr.studycontrol.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    public String name;
    public String description;
    public float purchase_price;
    public float production_price;
    public float sell_price;

    public Product() {
    }

    public Product(String name, String description, float purchase_price, float production_price, float sell_price) {
        this.name = name;
        this.description = description;
        this.purchase_price = purchase_price;
        this.production_price = production_price;
        this.sell_price = sell_price;
    }
}
