package org.example.flipkart.testdata;

import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.entity.Product;

import java.util.List;

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
        product.setCategory(CategoryTestData.getCategory());
        return product;
    }

    public static List<Product> mockProductDataList() {
        Product product = new Product();
        product.setProductName("Hawkins");
        product.setProductDescription("Kitchen");
        product.setProductPrice(2000);
        product.setProductQuantity(2);
        product.setCategory(CategoryTestData.getCategory());

        Product product1 = new Product();
        product1.setProductName("Fan");
        product1.setProductDescription("Home");
        product1.setProductPrice(1000);
        product1.setProductQuantity(0);
        product1.setCategory(CategoryTestData.getCategory());

        Product product2 = new Product();
        product2.setProductName("TV");
        product2.setProductDescription("Home");
        product2.setProductPrice(1000);
        product2.setProductQuantity(1);
        product2.setCategory(CategoryTestData.getCategory());

        return List.of(product, product1, product2);
    }
}
