package com.gravity.fastcommerce.entities.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
//@RedisHash("Product")
public class Product {

    @Id
    private String id;
    private int quantity;
    private int productId;
    private int price;
}