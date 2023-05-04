package com.joreijarr.studycontrol.repo;

import com.joreijarr.studycontrol.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
