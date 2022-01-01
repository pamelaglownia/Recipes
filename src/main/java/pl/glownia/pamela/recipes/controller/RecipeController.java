package pl.glownia.pamela.recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.glownia.pamela.recipes.service.RecipeService;
import pl.glownia.pamela.recipes.model.Recipe;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/add")
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe createdRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getCookbook();
    }

    @GetMapping("/{recipeId}")
    public Recipe getRecipeById(@PathVariable Long recipeId) {
        return recipeService.getChosenRecipe(recipeId);
    }

    @GetMapping("/search/{recipeCategory}")
    public List<Recipe> findRecipeByCategory(@PathVariable String recipeCategory) {
        return recipeService.findRecipeByCategory(recipeCategory);
    }

    @GetMapping("/search/")
    public List<Recipe> findRecipeByName(@RequestParam("name") String recipeName) {
        return recipeService.findRecipeByName(recipeName);
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateChosenRecipe(@PathVariable Long recipeId, @Valid @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}