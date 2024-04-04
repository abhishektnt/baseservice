package com.gravity.fastcommerce.repositories.nosql;

import com.gravity.fastcommerce.entities.nosql.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoSqlProductRepository extends MongoRepository<Product, String> {

    // Optional methods for custom queries (e.g., findByName)
}