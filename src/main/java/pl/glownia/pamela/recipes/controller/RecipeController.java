package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipes/new-recipe")
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getCookbook();
    }

    @GetMapping("/recipes/{recipeId}")
    public Recipe getRecipeById(@PathVariable Long recipeId) {
        return recipeService.getChosenRecipe(recipeId);
    }

    @GetMapping("/recipes/categories/{recipeCategory}")
    public List<Recipe> findRecipeByCategory(@PathVariable String recipeCategory) {
        return recipeService.findRecipeByCategory(recipeCategory);
    }

    @GetMapping("/recipes/names/{recipeName}")
    public List<Recipe> findRecipeByName(@PathVariable String recipeName) {
        return recipeService.findRecipeByName(recipeName);
    }

    @GetMapping("/recipes/my-recipes")
    @ResponseBody
    public Set<Recipe> getAllCurrentUsersRecipe() {
        return recipeService.findAllCurrentUsersRecipe();
    }

    @PutMapping("/recipes/{recipeId}")
    public ResponseEntity<Recipe> updateChosenRecipe(@PathVariable Long recipeId, @Valid @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.CREATED);
    }

    @DeleteMapping("/recipes/{recipeId}")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}