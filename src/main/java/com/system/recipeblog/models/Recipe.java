package com.system.recipeblog.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    /* Model Recipe
     * The model is mapped to Model Category with a many-to-many relationship
     * has id as its unique identifier and mapper in the union table
     * It also has a one-to-many relationship with Model Ingredient
     * Its table will have 1 id mapped to many ingredientIds
     * */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name = "prep_time")
    private Integer prepTime;

    @Column(name = "cook_time")
    private Integer cookTime;

    @Column(name = "servings")
    private Integer servings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
