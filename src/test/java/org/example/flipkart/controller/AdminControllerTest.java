package org.example.flipkart.controller;

import org.example.flipkart.dto.CategoryDTO;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.service.AdminService;
import org.example.flipkart.testdata.CategoryTestData;
import org.example.flipkart.testdata.ProductTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;


    @Test
    void createCategory_200() {
        //Mock
        CategoryDTO categoryDTO = CategoryTestData.createCategoryDTO();
        doNothing().when(adminService).createCategory(any());

        // function call
        adminController.createCategory(categoryDTO);

        // verification
        verify(adminService, times(1)).createCategory(any());
    }

    @Test
    void getAllCategories_200() {
        CategoryResponseDTO categoryResponseDTO = CategoryTestData.getCategoryResponseDTO();
        when(adminService.getAllCategories()).thenReturn(Collections.singletonList(categoryResponseDTO));
        adminController.getAllCategories();
        verify(adminService, times(1)).getAllCategories();
    }

    @Test
    void createProduct_200() {
        ProductDTO productDTO = ProductTestData.createProductDTO();
        doNothing().when(adminService).createProduct(any());
        adminController.createProduct(productDTO);
        verify(adminService, times(1)).createProduct(any());
    }

    @Test
    void updateProduct_200() {
        ProductDTO productDTO = ProductTestData.createProductDTO();
        doNothing().when(adminService).updateProduct(anyLong(), any());
        adminController.updateProduct(1L, productDTO);
        verify(adminService, times(1)).updateProduct(anyLong(), any());
    }

    @Test
    void deleteProduct_200() {
        doNothing().when(adminService).deleteProduct(anyLong());
        adminController.deleteProduct(1L);
        verify(adminService, times(1)).deleteProduct(anyLong());
    }
}
