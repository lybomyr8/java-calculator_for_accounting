package com.joreijarr.studycontrol.repo;

import com.joreijarr.studycontrol.models.Sell;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SellRepository extends CrudRepository<Sell, Long> {

    @Query(value = "SELECT sum(price) FROM Sell")
    public float sumSellPrice();


}
