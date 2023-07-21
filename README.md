## Recipe Blog REST Project Description

This project was imported from an existing recipe blog application that I own. The main objective behind this endeavor was to create two distinct projects. The first one serves as a showcase, utilizing Thymeleaf to render and present the recipes in a visually appealing manner. The second project, on the other hand, focuses on providing a RESTful API to expose the recipe data.

### RESTful API Project

This second project exposes the recipe data through a RESTful API, providing a straightforward way for developers and other applications to access and utilize the recipe information programmatically. This API can be easily integrated into various applications, such as mobile apps, front-end frameworks, or any other platforms that can consume RESTful services.

## Purpose

The purpose of creating these two projects is to illustrate different methods of interacting with the recipe blog data. By having a Thymeleaf-powered showcase and a RESTful API, this project showcases the versatility and potential uses of the underlying data.

## Here are the corresponding `curl` requests for each endpoint in the `RecipeController` class:

1. Create Recipe Endpoint:
   ```
   curl -X POST -H "Content-Type: application/json" -d '{
       "title": "Delicious Cake",
       "description": "A mouthwatering cake recipe",
       "categories": ["Dessert", "Baking"],
       "ingredients": ["Flour", "Sugar", "Eggs", "Butter"]
   }' http://localhost:8080/api/v1/recipeblog/create-recipe
   ```

2. List All Recipes Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog
   ```

3. List All Recipes According to Category Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/category/{id}
   ```
   Replace `{id}` with the actual `categoryId` you want to retrieve recipes for. For example, if the `categoryId` is 1, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/category/1
   ```

4. Get Recipe by ID Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/recipe/{id}
   ```
   Replace `{id}` with the actual `recipeId` you want to retrieve. For example, if the `recipeId` is 123, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/recipe/123
   ```

5. Update Recipe by ID Endpoint:
   ```
   curl -X PUT -H "Content-Type: application/json" -d '{
       "title": "Updated Cake",
       "description": "An even better cake recipe",
       "categories": ["Dessert", "Baking", "Special"],
       "ingredients": ["Flour", "Sugar", "Eggs", "Butter", "Vanilla Extract"]
   }' http://localhost:8080/api/v1/recipeblog/{id}
   ```
   Replace `{id}` with the actual `recipeId` you want to update. For example, if the `recipeId` is 123, the request will be:
   ```
   curl -X PUT -H "Content-Type: application/json" -d '{
       "title": "Updated Cake",
       "description": "An even better cake recipe",
       "categories": ["Dessert", "Baking", "Special"],
       "ingredients": ["Flour", "Sugar", "Eggs", "Butter", "Vanilla Extract"]
   }' http://localhost:8080/api/v1/recipeblog/123
   ```

6. Delete Recipe by ID Endpoint:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/{id}
   ```
   Replace `{id}` with the actual `recipeId` you want to delete. For example, if the `recipeId` is 123, the request will be:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/123
   ```

## Below are the `curl` requests for each endpoint in the `IngredientController` class:

1. Save or Update Ingredient to a Recipe Endpoint:
   ```
   curl -X POST -H "Content-Type: application/json" -d '{
       "name": "Sugar",
       "quantity": "2 cups",
       "recipe": {
           "id": 1
       }
   }' http://localhost:8080/api/v1/recipeblog/ingredients/create-or-save-ingredient
   ```

2. Retrieve Ingredients from a Recipe Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/ingredients/recipe/{id}
   ```
   Replace `{id}` with the actual `recipeId` for which you want to retrieve ingredients. For example, if the `recipeId` is 1, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/ingredients/recipe/1
   ```

3. Find Ingredient by ID Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/ingredients/ingredient/{id}
   ```
   Replace `{id}` with the actual `ingredientId` you want to retrieve. For example, if the `ingredientId` is 123, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/ingredients/ingredient/123
   ```

4. Delete Ingredient by ID Endpoint:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/ingredients/{id}
   ```
   Replace `{id}` with the actual `ingredientId` you want to delete. For example, if the `ingredientId` is 123, the request will be:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/ingredients/123
   ```
## Here are the `curl` requests for each endpoint in the `CategoryController` class:

1. Create Category Endpoint:
   ```
   curl -X POST -H "Content-Type: application/json" -d '{
       "name": "Dessert"
   }' http://localhost:8080/api/v1/recipeblog/categories/create-category
   ```

2. Update Category Endpoint:
   ```
   curl -X PUT -H "Content-Type: application/json" -d '{
       "name": "Updated Dessert"
   }' http://localhost:8080/api/v1/recipeblog/categories/{id}
   ```
   Replace `{id}` with the actual `categoryId` you want to update. For example, if the `categoryId` is 1, the request will be:
   ```
   curl -X PUT -H "Content-Type: application/json" -d '{
       "name": "Updated Dessert"
   }' http://localhost:8080/api/v1/recipeblog/categories/1
   ```

3. Get List of All Categories Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/categories
   ```

4. Get Categories According to Recipe Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/categories/recipe/{id}
   ```
   Replace `{id}` with the actual `recipeId` for which you want to retrieve categories. For example, if the `recipeId` is 1, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/categories/recipe/1
   ```

5. Retrieve Category by ID Endpoint:
   ```
   curl http://localhost:8080/api/v1/recipeblog/categories/{id}
   ```
   Replace `{id}` with the actual `categoryId` you want to retrieve. For example, if the `categoryId` is 123, the request will be:
   ```
   curl http://localhost:8080/api/v1/recipeblog/categories/123
   ```

6. Delete Category by ID Endpoint:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/categories/{id}
   ```
   Replace `{id}` with the actual `categoryId` you want to delete. For example, if the `categoryId` is 123, the request will be:
   ```
   curl -X DELETE http://localhost:8080/api/v1/recipeblog/categories/123
   ```
    
   Make sure to fill in the necessary JSON data (if required) for the `POST` and `PUT` requests.
