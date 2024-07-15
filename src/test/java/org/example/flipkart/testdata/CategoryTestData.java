package org.example.flipkart.testdata;

import org.example.flipkart.dto.CategoryDTO;
import org.example.flipkart.dto.CategoryResponseDTO;
import org.example.flipkart.entity.Category;

public class CategoryTestData {
    public static CategoryDTO createCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("Kitchen");
        categoryDTO.setCategoryDescription("Home Data");
        return categoryDTO;
    }

    public static CategoryResponseDTO getCategoryResponseDTO() {
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName("Kitchen");
        categoryResponseDTO.setCategoryId(1L);
        return categoryResponseDTO;
    }

    public static Category getCategory() {
        Category category = new Category();
        category.setCategoryName("Kitchen");
        category.setDescription("Home Data");
        category.setId(1L);
        return category;
    }
}
