package com.gravity.fastcommerce.controllers.nosql;

import com.gravity.fastcommerce.entities.nosql.Product;
import com.gravity.fastcommerce.services.nosql.NoProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "NoSqlProduct", description = "Product management APIs")
@RestController("nosqlcontroller")
@RequestMapping("/nosql/api/v1/products")  // Base path for product API endpoints
public class NoSqlProductController {
    private static final Logger logger = LoggerFactory.getLogger(NoSqlProductController.class);

    @Autowired
    private NoProductServiceImpl noProductServiceImpl;

    @Operation(
            summary = "Adds a Product",
            description = "Adds a Product object in No-Sql database. The response is Product object with id, title, description and published status.",
            tags = { "NoSqlProduct", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping  // Creates a new product
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        logger.info("Entering createProduct method with product: {}", product);
        Product savedProduct = noProductServiceImpl.createProduct(product);
        logger.info("Exiting createProduct method with saved product: {}", savedProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve a Product by Id",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, description and published status.",
            tags = { "NoSqlProduct", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")  // Gets a product by ID
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        logger.info("Entering getProductById method with id: {}", id);
        Product product = noProductServiceImpl.getProductById(id);
        if (product != null) {
            logger.info("Exiting getProductById method with product: {}", product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            logger.info("Exiting getProductById method with NOT_FOUND status");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Retrieve all the Products",
            description = "Gets all the Product. The response is Product object with id, title, description and published status.",
            tags = { "NoSqlProduct", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping  // Gets all products
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        logger.info("Entering getAllProducts method");
        Iterable<Product> products = noProductServiceImpl.getAllProducts();
        logger.info("Exiting getAllProducts method with products: {}", products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(
            summary = "Updates an existing Product",
            description = "Updates an existing the Product. The response is Product object with id, title, description and published status.",
            tags = { "NoSqlProduct", "update" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping // Updates a product by ID
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        logger.info("Entering updateProduct method with product: {}", product);
        Product updatedProduct = noProductServiceImpl.updateProduct(product);
        if (updatedProduct != null) {
            logger.info("Exiting updateProduct method with updated product: {}", updatedProduct);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            logger.info("Exiting updateProduct method with NOT_FOUND status");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Deletes an existing Product with matching id.",
            description = "Deletes an existing the Product as per input product id.",
            tags = { "NoSqlProduct", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")  // Deletes a product by ID
    public ResponseEntity deleteProduct(@PathVariable String id) {
        logger.info("Entering deleteProduct method with id: {}", id);
        noProductServiceImpl.deleteProduct(id);
        logger.info("Exiting deleteProduct method with NO_CONTENT status");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
