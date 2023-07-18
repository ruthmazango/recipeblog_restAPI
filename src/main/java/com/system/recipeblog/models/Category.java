package com.system.recipeblog.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    /* Model Category
     * The model is mapped to Model Recipes with a many-to-many relationship
     * has id as its unique identifier and mapper in the union table
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
