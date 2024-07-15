package org.example.flipkart.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_category", referencedColumnName = "id", nullable = false)
    private Category category;
}
