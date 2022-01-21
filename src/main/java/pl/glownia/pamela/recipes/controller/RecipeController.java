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
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @PostMapping("/new")
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

    @GetMapping("/myrecipes")
    @ResponseBody
    public Set<Recipe> getAllUsersRecipe(){
        return recipeService.findAllUsersRecipe();
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateChosenRecipe(@PathVariable Long recipeId, @Valid @RequestBody Recipe recipe) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipeId, recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeId}")
    public void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}