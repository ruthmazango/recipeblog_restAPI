package com.system.recipeblog.services;

import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.IngredientRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService{
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    @Override
    public Ingredient saveOrUpdateIngredient(Ingredient ingredient, Recipe recipe) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipe.getId());

        if (!recipeOptional.isPresent()) {
            System.out.println("Recipe not found");
            return null; // Or throw an exception if necessary
        }

        recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setTitle(ingredient.getTitle());
            ingredientFound.setAmount(ingredient.getAmount());
        } else {
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return ingredientRepository.save(ingredient);
    }
    //Return All Ingredients in a recipe
    @Override
    public List<Ingredient> getAllIngredients(Recipe recipe) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipe.getId());
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found for id: " + recipe.getId());
        }
        recipe = recipeOptional.get();
        return new ArrayList<>(recipe.getIngredients());
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        Optional<Ingredient> ingredientOptional=ingredientRepository.findById(id);
        if(!ingredientOptional.isPresent()){
            throw new RuntimeException("Ingredient not found with id: " + id);
        }
        return ingredientRepository.findById(id);
    }

    @Override
    public void deleteIngredient(Long id) {
        Optional<Ingredient> ingredientOptional=ingredientRepository.findById(id);
        if(!ingredientOptional.isPresent()){
            throw new RuntimeException("Ingredient not found with id: " + id);
        }
        ingredientRepository.deleteById(id);
    }
}
