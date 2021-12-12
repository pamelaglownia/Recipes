package pl.glownia.pamela.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    void addRecipe(@RequestBody Recipe recipe) {
        cookbook.addRecipe(recipe);
    }

    @PostMapping("/delete/{recipeId}")
    void deleteRecipe(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
    }
}