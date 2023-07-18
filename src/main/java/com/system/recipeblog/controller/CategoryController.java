package com.system.recipeblog.controller;
import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.services.CategoryService;
import com.system.recipeblog.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200") //can be changed to suit the localhost of frontend framework
@RequestMapping("/api/v1/recipeblog/categories") //link to enpoints defined in this controller
public class CategoryController {

    private CategoryService categoryService;
    private RecipeService recipeService;
    //create category endpoint
    @PostMapping("/create-category")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    //update category endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()){
            categoryService.updateCategory(category, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(category);
        }
        return ResponseEntity.notFound().build();
    }
    //get list of all categories endpoint
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
       List<Category> categories= categoryService.getAllCategories();
       return ResponseEntity.ok(categories);
    }
    //get categories according to recipe endpoint
    @GetMapping("/recipe/{id}")
    public ResponseEntity<List<Category>> getAllCategoriesByRecipe(@PathVariable("id") Long recipeId){
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        Optional<Recipe> recipeOptional = recipeService.findById(recipeId);
        if (!recipeOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        List<Category> categories = categoryService.getAllCategoriesByRecipe(recipe);
        return ResponseEntity.ok(categories);
    }
    //retrieve category by id endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("/{id}") Long id){
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            return ResponseEntity.ok(category.get());
        }
        return ResponseEntity.notFound().build();
    }
    //delete category by id endpoint
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "Deleted successfully";
    }
}
