package com.gravity.fastcommerce.services.sql;

import com.gravity.fastcommerce.entities.sql.Product;
import com.gravity.fastcommerce.repositories.sql.SqlProductRepository;
import org.springframework.stereotype.Service;

@Service
public class SqlProductServiceImpl implements SqlProductService {

    private final SqlProductRepository sqlProductRepository;

//    @Autowired
//    private RedisTemplate<String, Product> redisTemplate;

    public SqlProductServiceImpl(SqlProductRepository sqlProductRepository) {
        this.sqlProductRepository = sqlProductRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return sqlProductRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return sqlProductRepository.findById(id).orElse(null);
//        Optional<Product> obj = Optional.ofNullable(redisTemplate.opsForValue().get(id));
//        return obj.orElseGet(() -> sqlProductRepository.findById(id).orElse(null));

//        Product product = redisTemplate.opsForValue().get(id);
//        if (product == null) {
//            Optional<Product> optionalProduct = sqlProductRepository.findById(id);
//            optionalProduct.ifPresent(value -> redisTemplate.opsForValue().set(String.valueOf(id), value, 5, TimeUnit.MINUTES)); // Cache for 5 minutes
//            return optionalProduct.orElse(null);
//        } else {
//            return product;
//        }


    }

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

    @Override
    public void deleteProduct(Integer id) {
        sqlProductRepository.deleteById(id);
    }
}
