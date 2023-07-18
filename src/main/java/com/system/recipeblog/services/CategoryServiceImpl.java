package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.CategoryRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategoriesByRecipe(Recipe recipe) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipe.getId());
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found with id: " + recipe.getId());
        }
        recipe = recipeOptional.get();
        return (List<Category>) recipe.getCategories();

    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()) {
            throw new RuntimeException("Category not found with id: " + id);
        } return categoryRepository.findById(id);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category category1 = categoryRepository.findById(id).get();
        if(id==null){
            System.out.println("Category does not exist");
        }else{
            category1.setDescription(category.getDescription());
            category1.setTitle(category.getTitle());
        }return categoryRepository.save(category1);
    }
    @Override
    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(!categoryOptional.isPresent()) {
            System.out.println("Category does not exist");
        }
        categoryRepository.deleteById(id);
    }
}
