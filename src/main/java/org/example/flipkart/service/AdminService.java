package org.example.flipkart.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.flipkart.dto.CategoryDTO;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.dto.ProductDTO;
import org.example.flipkart.entity.Category;
import org.example.flipkart.entity.Product;
import org.example.flipkart.exception.BadRequestException;
import org.example.flipkart.exception.ResourceNotFoundException;
import org.example.flipkart.repository.CategoryRepository;
import org.example.flipkart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void createProduct(ProductDTO productDTO) {
        Category category = checkCategoryExists(productDTO.getProductCategoryId());
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductQuantity(productDTO.getProductQuantity());
        product.setCategory(category);
        productRepository.save(product);
        log.info("Product created");

    }

    private Category checkCategoryExists(Integer productCategoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(productCategoryId);
        if (categoryOptional.isEmpty()) {
            throw new ResourceNotFoundException("Category not found with id: " + productCategoryId);
        }
        return categoryOptional.get();
    }

    public void updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        Category category = checkCategoryExists(productDTO.getProductCategoryId());
        Product product = productOptional.get(); // DB values
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductQuantity(productDTO.getProductQuantity());
        product.setCategory(category);
        productRepository.save(product);
        log.info("Product updated");

    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        log.info("Product deleted successfully");
    }

    public void createCategory(CategoryDTO categoryDTO) {

        boolean isCategoryExists = categoryRepository.existsByCategoryName(categoryDTO.getCategoryName());
        if (isCategoryExists) {
            throw new BadRequestException("Category already exists with name: " + categoryDTO.getCategoryName());
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getCategoryDescription());
        categoryRepository.save(category);
        log.info("Category created successfully");
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoriesDTO = new ArrayList<>();
        for(Category category: categories) {
            CategoryResponseDTO categoryDTO = new CategoryResponseDTO();
            categoryDTO.setCategoryName(category.getCategoryName());
            categoryDTO.setCategoryId(category.getId());
            categoriesDTO.add(categoryDTO);
        }
        return categoriesDTO;
    }
}
