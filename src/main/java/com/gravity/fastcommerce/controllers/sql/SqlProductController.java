package com.gravity.fastcommerce.controllers.sql;

import com.gravity.fastcommerce.entities.sql.Product;
import com.gravity.fastcommerce.services.sql.SqlProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "SqlProduct", description = "Product management APIs")
@RestController("sqlcontroller")
@RequestMapping("/api/v1/sql/products")
public class SqlProductController {
    private static final Logger logger = LoggerFactory.getLogger(SqlProductController.class);
    private final SqlProductService sqlProductService;

    public SqlProductController(SqlProductService sqlProductService) {
        this.sqlProductService = sqlProductService;
    }

    @Operation(
            summary = "Adds a Product",
            description = "Adds a Product object in No-Sql database. The response is Product object with id, title, description and published status.",
            tags = { "SqlProduct", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = com.gravity.fastcommerce.entities.nosql.Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        logger.info("Entering createProduct method with product: {}", product);
        Product savedProduct = sqlProductService.createProduct(product);
        logger.info("Exiting createProduct method with saved product: {}", savedProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Retrieve a Product by Id",
            description = "Get a Product object by specifying its id. The response is Product object with id, title, description and published status.",
            tags = { "SqlProduct", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = com.gravity.fastcommerce.entities.nosql.Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")  // Gets a product by ID
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        logger.info("Entering getProductById method with id: {}", id);
        Product product = sqlProductService.getProductById(id);
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
            tags = { "SqlProduct", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = com.gravity.fastcommerce.entities.nosql.Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping  // Gets all products
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        logger.info("Entering getAllProducts method");
        Iterable<Product> products = sqlProductService.getAllProducts();
        logger.info("Exiting getAllProducts method with products: {}", products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(
            summary = "Updates an existing Product",
            description = "Updates an existing the Product. The response is Product object with id, title, description and published status.",
            tags = { "SqlProduct", "update" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = com.gravity.fastcommerce.entities.nosql.Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{id}")  // Updates a product by ID
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        logger.info("Entering updateProduct method with product: {}", product);
        Product updatedProduct = sqlProductService.updateProduct(product);
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
            tags = { "SqlProduct", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = com.gravity.fastcommerce.entities.nosql.Product.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")  // Deletes a product by ID
    public ResponseEntity deleteProduct(@PathVariable int id) {
        logger.info("Entering deleteProduct method with id: {}", id);
        sqlProductService.deleteProduct(id);
        logger.info("Exiting deleteProduct method with NO_CONTENT status");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}