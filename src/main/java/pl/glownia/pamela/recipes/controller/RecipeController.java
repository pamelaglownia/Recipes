package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.service.RecipeService;
import pl.glownia.pamela.recipes.model.Recipe;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    RecipeService cookbook;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getCookbook();
    }

    @GetMapping("/{recipeId}")
    public Optional<Recipe> getRecipeById(@PathVariable Long recipeId) {
        return cookbook.getChosenRecipe(recipeId);
    }

    @PostMapping("/add")
    ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe createdRecipe = cookbook.addRecipe(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{recipeId}")
    void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}