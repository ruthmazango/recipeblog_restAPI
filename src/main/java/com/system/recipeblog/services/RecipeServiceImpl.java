package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.CategoryRepository;
import com.system.recipeblog.repositories.IngredientRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;
    private Recipe recipe;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Recipe saveRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients) {

        recipe.setTitle(recipe.getTitle());
        recipe.setDescription(recipe.getDescription());
        recipe.setServings(recipe.getServings());
        recipe.setCookTime(recipe.getCookTime());
        recipe.setPrepTime(recipe.getPrepTime());
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);
        return recipeRepository.save(recipe);

    }

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    @Override
    public List<Recipe> getRecipesByCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(category.getId());
        if (!categoryOptional.isPresent()){
            throw new RuntimeException("Category not found with id: " + category.getId());
        }
        category = categoryOptional.get();
        return (List<Recipe>) category.getRecipes();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found with that");
        }
        return Optional.ofNullable(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients, Long id) {
        Recipe recipe1 = recipeRepository.findById(id).get();
        if(id == null){
            throw new RuntimeException("Recipe not found with that id");
        } else {
            recipe1.setTitle(recipe.getTitle());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setServings(recipe.getServings());
            recipe1.setCookTime(recipe.getCookTime());
            recipe1.setPrepTime(recipe.getPrepTime());
            recipe1.setCategories(categories);
            recipe1.setIngredients(ingredients);
        } return recipeRepository.save(recipe1);
    }

    @Override
    public void deleteRecipe(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }
}
