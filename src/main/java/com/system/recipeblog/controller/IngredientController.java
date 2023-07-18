package com.system.recipeblog.controller;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.services.IngredientService;
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
@RequestMapping("/api/v1/recipeblog/ingredients/")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;
    //save or update ingredient to a recipe endpoint
    @PostMapping("/create-or-save-ingredient")
    public ResponseEntity<Ingredient> saveOrUpdateIngredient(@RequestBody Ingredient ingredient){
        ingredientService.saveOrUpdateIngredient(ingredient, ingredient.getRecipe());
        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }
    //retrieve ingredients from a recipe endpoint
    @GetMapping("/recipe/{id}")
    public ResponseEntity<List<Ingredient>> getAllIngredientsByRecipe(@PathVariable("id") Long recipeId){
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Optional<Recipe> recipeOptional = recipeService.findById(recipeId);
        if (!recipeOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        List<Ingredient> ingredients = ingredientService.getAllIngredients(recipe);
        return ResponseEntity.ok(ingredients);
    }
    //find ingredient by id endpoint
    @GetMapping("/ingredient/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") Long id){
        Optional<Ingredient> ingredient = ingredientService.findById(id);
        if(ingredient.isPresent()){
            return ResponseEntity.ok(ingredient.get());
        }return ResponseEntity.notFound().build();
    }
    //delete ingredient by id endpoint
    @DeleteMapping("/{id}")
    public String deleteIngredient(@PathVariable("id") Long id){
        recipeService.deleteRecipe(id);
        return "Deleted successfully";
    }
}
