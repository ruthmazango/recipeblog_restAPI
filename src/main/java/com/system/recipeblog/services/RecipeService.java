package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;

import java.util.*;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients);
    List<Recipe> getRecipes();
    List<Recipe> getRecipesByCategory(Category category);
    Optional<Recipe> findById(Long id);
    Recipe updateRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients, Long id);
    void deleteRecipe(Long id);
}