package com.gravity.fastcommerce.services.nosql;

import com.gravity.fastcommerce.entities.nosql.Product;

public interface NoSqlProductService {

    Product createProduct(Product product);

    Product getProductById(String id);

    Iterable<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(String id);
}