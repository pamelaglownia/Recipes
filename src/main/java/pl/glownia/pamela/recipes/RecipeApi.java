package pl.glownia.pamela.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApi {
    RecipeManager cookbook;

    @Autowired
    public RecipeApi(RecipeManager cookbook) {
        this.cookbook = cookbook;
    }

    @GetMapping("/all")
    public HashMap<Integer, Recipe> getAllRecipes() {
        return cookbook.getCookbook();
    }

    @GetMapping("/{recipeId}")
    public Optional<Recipe> getRecipeById(@PathVariable Integer recipeId) {
        return cookbook.getChosenRecipe(recipeId);
    }

    @PostMapping("/add")
    void addRecipe(@RequestBody Recipe recipe) {
        cookbook.addRecipe(recipe);
    }

    @PostMapping("/delete/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        cookbook.deleteRecipe(recipeId);
    }
}