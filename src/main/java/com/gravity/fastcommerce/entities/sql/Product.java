package com.gravity.fastcommerce.entities.sql;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "product")
@Data
//@RedisHash("Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;
    private Integer price;
    private Integer quantity;
}