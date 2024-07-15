package org.example.flipkart.exception;

public class ProductInsufficientInventoryException extends RuntimeException {
    public ProductInsufficientInventoryException(String message) {
        super(message);
    }
}
