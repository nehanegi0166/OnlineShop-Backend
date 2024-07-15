package org.example.flipkart.service;

import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.entity.Category;
import org.example.flipkart.entity.Product;
import org.example.flipkart.exception.ProductInsufficientInventoryException;
import org.example.flipkart.exception.ResourceNotFoundException;
import org.example.flipkart.repository.CategoryRepository;
import org.example.flipkart.repository.ProductRepository;
import org.example.flipkart.testdata.CategoryTestData;
import org.example.flipkart.testdata.ProductTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    ProductRepository productRepository;
    @Mock
    CategoryRepository categoryRepository;

    @Test
    void getAllProducts_200() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(ProductTestData.mockProductData()));
        List<ProductResponseDTO> response = customerService.getAllProducts();
        assertFalse(response.isEmpty());
    }

    @Test
    void getProductByCategoryId_200() {
        Category category = CategoryTestData.getCategory();
        List<Product> productList = ProductTestData.mockProductDataList();
        category.setProducts(productList);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        List<ProductResponseDTO> response = customerService.getProductByCategoryId(1, Optional.empty());
        assertTrue(response.size() > 0);
    }

    @Test
    void getProductByCategoryPrice_200() {
        Category category = CategoryTestData.getCategory();
        List<Product> productList = ProductTestData.mockProductDataList();
        category.setProducts(productList);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        List<ProductResponseDTO> response = customerService.getProductByCategoryId(1, Optional.of(1000));
        assertTrue(response.size() == 1);
    }

    @Test
    void getProductByCategoryId_CategoryNotFound() {
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerService.getProductByCategoryId(1,  Optional.of(1000)));
    }

    @Test
    void getProductByCategoryId_InsufficientInventory() {
        Category category = CategoryTestData.getCategory();
        List<Product> productList = ProductTestData.mockProductDataList();
        category.setProducts(Collections.singletonList(productList.get(1)));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        assertThrows(ProductInsufficientInventoryException.class, () -> customerService.getProductByCategoryId(1, Optional.of(3000)));
    }
}
