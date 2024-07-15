package org.example.flipkart.service;

import lombok.RequiredArgsConstructor;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductResponseDTO;
import org.example.flipkart.entity.Category;
import org.example.flipkart.entity.Product;
import org.example.flipkart.exception.ProductInsufficientInventoryException;
import org.example.flipkart.exception.ResourceNotFoundException;
import org.example.flipkart.repository.CategoryRepository;
import org.example.flipkart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return mapProductToProductResponseDTO(productList);
    }

    public List<ProductResponseDTO> getProductByCategoryId(int categoryId, Optional<Integer> priceOptional) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + categoryId);
        }
        Category category = categoryOptional.get();
        // filter product with quantity
        List<Product> productList = category.getProducts().stream().filter(product -> product.getProductQuantity() > 0).toList();
        // if all product not available
        if (productList.isEmpty()) {
            throw new ProductInsufficientInventoryException("No products available with sufficient inventory");
        }
        if (priceOptional.isEmpty()) {
            // get response by category id
            return mapProductToProductResponseDTO(productList);
        }

        // check product price filter applicable or not
        List<Product> filteredProductList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductPrice() == priceOptional.get()) {
                filteredProductList.add(product);
            }
        }
        // get response by category id and price
        return mapProductToProductResponseDTO(filteredProductList);
    }

    private List<ProductResponseDTO> mapProductToProductResponseDTO(List<Product> productList) {
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        for (Product product : productList) {
            //Custom response object
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setId(product.getId());
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setProductDescription(product.getProductDescription());
            productResponseDTO.setProductPrice(product.getProductPrice());
            productResponseDTO.setProductQuantity(product.getProductQuantity());
            productResponseDTO.setProductCategoryName(product.getCategory().getCategoryName());
            productResponseDTOList.add(productResponseDTO);
        }
        return productResponseDTOList;
    }
}
