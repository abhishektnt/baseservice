package com.gravity.fastcommerce.services.nosql;

import com.gravity.fastcommerce.entities.nosql.Product;
import com.gravity.fastcommerce.repositories.nosql.NoSqlProductRepository;
import org.springframework.stereotype.Service;

@Service
public class NoProductServiceImpl implements NoSqlProductService{

    private final NoSqlProductRepository noSqlProductRepository;

    public NoProductServiceImpl(NoSqlProductRepository noSqlProductRepository) {
        this.noSqlProductRepository = noSqlProductRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return noSqlProductRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        return noSqlProductRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return noSqlProductRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = noSqlProductRepository.findById(String.valueOf(product.getProductId())).orElse(null);
        if (existingProduct != null) {
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            return noSqlProductRepository.save(existingProduct);
        } else {
            return null; // Handle product not found case
        }
    }

    @Override
    public void deleteProduct(String id) {
        noSqlProductRepository.deleteById(id);
    }

}