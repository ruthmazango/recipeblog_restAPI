package com.system.recipeblog.models;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    /* Model Ingredient
     * The model is mapped to Model Recipes with a many-to-one relationship
     * has id as its unique identifier and mapper in the union table
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(name = "title")
    public String title;

    @Column(name = "amount")
    public String amount;

    @ManyToOne
    private Recipe recipe;

}
