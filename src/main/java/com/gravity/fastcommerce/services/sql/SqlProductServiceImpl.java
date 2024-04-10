package com.gravity.fastcommerce.services.sql;

import com.gravity.fastcommerce.entities.sql.Product;
import com.gravity.fastcommerce.repositories.sql.SqlProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SqlProductServiceImpl implements SqlProductService {

    private final SqlProductRepository sqlProductRepository;

//    @Autowired
//    private RedisTemplate<String, Product> redisTemplate;

    public SqlProductServiceImpl(SqlProductRepository sqlProductRepository) {
        this.sqlProductRepository = sqlProductRepository;
    }

    @CachePut(value = "Product", key = "#product.id")
    @Override
    public Product createProduct(Product product) {
        return sqlProductRepository.save(product);
    }

    @Cacheable(value = "Product", key = "#id")
    @Override
    public Product getProductById(Integer id) {
        return sqlProductRepository.findById(id).orElse(null);
    }


    @Cacheable(value = "Product", key = "'all'")
    @Override
    public Iterable<Product> getAllProducts() {
        return sqlProductRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = sqlProductRepository.findById(product.getProductId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            return sqlProductRepository.save(existingProduct);
        } else {
            return null; // Handle product not found case
        }
    }
    @CacheEvict(key = "#id",value = "Product")
    @Override
    public void deleteProduct(Integer id) {
        sqlProductRepository.deleteById(id);
    }
}
