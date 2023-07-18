package com.system.recipeblog.services;

import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Ingredient saveOrUpdateIngredient(Ingredient ingredient, Recipe recipe);
    List<Ingredient> getAllIngredients(Recipe recipe);
    Optional<Ingredient> findById(Long id);
    void deleteIngredient(Long id);
}
