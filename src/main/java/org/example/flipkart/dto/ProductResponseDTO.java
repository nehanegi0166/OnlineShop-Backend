package org.example.flipkart.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private String productCategoryName;
}
