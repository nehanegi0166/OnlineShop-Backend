package org.example.flipkart.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private Integer productCategoryId;
}
