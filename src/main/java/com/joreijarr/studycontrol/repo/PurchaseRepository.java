package com.joreijarr.studycontrol.repo;

import com.joreijarr.studycontrol.models.Purchase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {


    @Query(value = "SELECT sum(price) FROM Purchase")
    public float sumPurchases();


    @Query(value = "SELECT sum(product_count) FROM Purchase where product = ?1")
    public float countPurchases(Long id);

}
