package com.joreijarr.studycontrol.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long product;
    public int product_count;
    public float price;

    public Purchase(Long product, int product_count, float price) {
        this.product = product;
        this.product_count = product_count;
        this.price = price;
    }

    public Purchase() {
    }
}
