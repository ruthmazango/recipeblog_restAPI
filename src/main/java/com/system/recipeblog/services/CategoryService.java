package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    List<Category> getAllCategoriesByRecipe(Recipe recipe);
    Optional<Category> findById(Long id);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
}
