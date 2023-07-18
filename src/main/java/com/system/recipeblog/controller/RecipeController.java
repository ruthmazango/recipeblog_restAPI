package com.system.recipeblog.controller;
import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.services.CategoryService;
import com.system.recipeblog.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200") //can be changed to suit the localhost of frontend framework
@RequestMapping("/api/v1/recipeblog")
public class RecipeController {
    private RecipeService recipeService;
    private CategoryService categoryService;
    //create recipe endpoint
    @PostMapping("/create-recipe")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
        recipeService.saveRecipe(recipe, recipe.getCategories(), recipe.getIngredients());
        return new ResponseEntity<>(recipe, HttpStatus.CREATED);
    }
    //list all recipes endpoint
    @GetMapping
    public List<Recipe> getRecipes(){
        return recipeService.getRecipes();
    }
    //list all recipes according to Category endpoint
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@PathVariable("id") Long categoryId){
        Category category = new Category();
        category.setId(categoryId);

        Optional<Category> categoryOptional = categoryService.findById(categoryId);
        if (!categoryOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        List<Recipe> recipes = recipeService.getRecipesByCategory(category);
        return ResponseEntity.ok(recipes);
    }
    //get recipe by id endpoint
    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable Long id){
        Optional<Recipe> recipe = recipeService.findById(id);
        if(recipe.isPresent()){
            return ResponseEntity.ok(recipe.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //update recipe by id endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe){
        recipeService.updateRecipe(recipe, recipe.getCategories(), recipe.getIngredients(), id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(recipe);
    }
    //delete recipe by id endpoint
    @DeleteMapping("/{id}")
    public String deleteRecipe(@PathVariable("id") Long id){
        recipeService.deleteRecipe(id);
        return "Recipe successfully deleted";
    }
}
