package org.example.flipkart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.flipkart.dto.CategoryDTO;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;

    @Operation(summary = "Create category")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/create-category")
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        adminService.createCategory(categoryDTO);
    }

    @Operation(summary = "Get all category list")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category list fetched successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(adminService.getAllCategories());
    }

    @Operation(summary = "Create product")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/create-product")
    public void createProduct(@RequestBody ProductDTO productDTO) {
        adminService.createProduct(productDTO);
    }


    @Operation(summary = "Update product")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/update-product/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        adminService.updateProduct(id, productDTO);
    }

    @Operation(summary = "Delete product by product id")
    //Swagger Doc
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/delete-product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        adminService.deleteProduct(id);
    }


}
