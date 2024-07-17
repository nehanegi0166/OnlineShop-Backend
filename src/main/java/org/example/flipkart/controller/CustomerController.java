package org.example.flipkart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Get all product list")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product list fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(customerService.getAllProducts());
    }

    @Operation(summary = "Get products by category id")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get product by category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/product/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategoryId(@PathVariable Integer categoryId) {
        Optional<Integer> price = Optional.empty();
        return ResponseEntity.ok(customerService.getProductByCategoryId(categoryId, price));
    }

    @Operation(summary = "Get products by category id and price")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get product by category"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/product/{categoryId}/{price}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategoryIdAndPrice(@PathVariable Integer categoryId, @PathVariable Optional<Integer> price) {
        return ResponseEntity.ok(customerService.getProductByCategoryId(categoryId, price));
    }
}
