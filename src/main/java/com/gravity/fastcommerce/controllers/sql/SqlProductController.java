package com.gravity.fastcommerce.controllers.sql;

import com.gravity.fastcommerce.entities.sql.Product;
import com.gravity.fastcommerce.services.sql.SqlProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "SqlProduct", description = "Product management APIs")
@RestController("sqlcontroller")
@RequestMapping("/api/v1/sql/products")
public class SqlProductController {

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
        Product savedProduct = sqlProductService.createProduct(product);
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
        Product product = sqlProductService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
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
        Iterable<Product> products = sqlProductService.getAllProducts();
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
        Product updatedProduct = sqlProductService.updateProduct(product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
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
        sqlProductService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}