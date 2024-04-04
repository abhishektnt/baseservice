package com.gravity.fastcommerce.services.sql;

import com.gravity.fastcommerce.entities.sql.Product;

public interface SqlProductService {

    Product createProduct(Product product);

    Product getProductById(Integer id);

    Iterable<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(Integer id);
}