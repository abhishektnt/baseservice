package com.gravity.fastcommerce.repositories.sql;

import com.gravity.fastcommerce.entities.sql.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlProductRepository extends JpaRepository<Product, Integer> {

}