package com.joreijarr.studycontrol.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long product;
    public int count;
    public float price;
    public float profit;

    public Sell(Long product, int count, float price, float profit) {
        this.product = product;
        this.count = count;
        this.price = price;
        this.profit = profit;
    }

    public Sell() {
    }
}
