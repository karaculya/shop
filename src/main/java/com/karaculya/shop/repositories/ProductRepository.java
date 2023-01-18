package com.karaculya.shop.repositories;

import com.karaculya.shop.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
