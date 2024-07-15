package org.example.flipkart.testdata;

import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.entity.Product;

public class ProductTestData {
    public static ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Hawkins");
        productDTO.setProductDescription("Kitchen");
        productDTO.setProductPrice(2000);
        productDTO.setProductQuantity(2);
        productDTO.setProductCategoryId(1);
        return productDTO;
    }

    public static ProductResponseDTO getProductResponseDTO() {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(1L);
        productResponseDTO.setProductName("Hawkins");
        productResponseDTO.setProductDescription("Kitchen");
        productResponseDTO.setProductPrice(2000);
        productResponseDTO.setProductQuantity(2);
        productResponseDTO.setProductCategoryName("Home");
        return productResponseDTO;
    }

    public static Product mockProductData() {
        Product product = new Product();
        product.setProductName("Hawkins");
        product.setProductDescription("Kitchen");
        product.setProductPrice(2000);
        product.setProductQuantity(2);
        return product;
    }
}
