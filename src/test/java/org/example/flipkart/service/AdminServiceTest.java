package org.example.flipkart.service;

import org.example.flipkart.dto.CategoryDTO;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.entity.Category;
import org.example.flipkart.entity.Product;
import org.example.flipkart.exception.BadRequestException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void createProduct_200(){
        ProductDTO productDTO = ProductTestData.createProductDTO();
        Category category = CategoryTestData.getCategory();
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        adminService.createProduct(productDTO);
        verify(productRepository,times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_categoryNotFound(){
        ProductDTO productDTO = ProductTestData.createProductDTO();
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> adminService.createProduct(productDTO));
    }

    @Test
    void updateProduct_200(){
        ProductDTO productDTO = ProductTestData.createProductDTO();
        Product product = ProductTestData.mockProductData();
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryTestData.getCategory()));
        adminService.updateProduct(1L,productDTO);
        verify(productRepository,times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct_ProductNotFound(){
        ProductDTO productDTO = ProductTestData.createProductDTO();
        when(productRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> adminService.updateProduct(1L,productDTO));
    }

    @Test
    void updateProduct_CategoryNotFound(){
        ProductDTO productDTO = ProductTestData.createProductDTO();
        when(productRepository.findById(any())).thenReturn(Optional.of(ProductTestData.mockProductData()));
        when(categoryRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> adminService.updateProduct(1L,productDTO));
    }

    @Test
    void deleteProduct(){
        doNothing().when(productRepository).deleteById(any());
        adminService.deleteProduct(1L);
    }

    @Test
    void createCategory_200(){
        CategoryDTO categoryDTO = CategoryTestData.createCategoryDTO();
        when(categoryRepository.existsByCategoryName(any())).thenReturn(false);
        adminService.createCategory(categoryDTO);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void createCategory_categoryExists(){
        CategoryDTO categoryDTO = CategoryTestData.createCategoryDTO();
        when(categoryRepository.existsByCategoryName(any())).thenReturn(true);
        assertThrows(BadRequestException.class, () -> adminService.createCategory(categoryDTO));
    }

    @Test
    void getAllCategories(){
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(CategoryTestData.getCategory()));
        List<CategoryResponseDTO> response = adminService.getAllCategories();
        assertFalse(response.isEmpty());
    }
}
