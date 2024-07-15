package org.example.flipkart.controller;

import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.service.CustomerService;
import org.example.flipkart.testdata.ProductTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @Test
    public void getProduct_200() {
        ProductResponseDTO productResponseDTO = ProductTestData.getProductResponseDTO();
        when(customerService.getAllProducts()).thenReturn(Collections.singletonList(productResponseDTO));
        customerController.getAllProducts();
        verify(customerService, times(1)).getAllProducts();

    }

    @Test
    public void getProductByCategoryId_200() {
        ProductResponseDTO productResponseDTO = ProductTestData.getProductResponseDTO();
        when(customerService.getProductByCategoryId(1, Optional.empty())).thenReturn(Collections.singletonList(productResponseDTO));
        customerController.getProductByCategoryId(1);
        verify(customerService, times(1)).getProductByCategoryId(1, Optional.empty());
    }

    @Test
    public void getProductByCategoryIdAndPrice_200(){
        ProductResponseDTO productResponseDTO = ProductTestData.getProductResponseDTO();
        when(customerService.getProductByCategoryId(1,Optional.empty())).thenReturn(Collections.singletonList(productResponseDTO));
        customerController.getProductByCategoryIdAndPrice(1,Optional.empty());
        verify(customerService, times(1)).getProductByCategoryId(1,Optional.empty());

    }
}
