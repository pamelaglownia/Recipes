package pl.glownia.pamela.recipes;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApi {

    private final HashMap<Integer, Recipe> cookbook;

    public RecipeApi() {
        cookbook = new HashMap<>();
        cookbook.put(cookbook.size() + 1, new Recipe("blabla", "description....", "ingredients...",
                "directions...."));
        cookbook.put(cookbook.size() + 1, new Recipe("Hot chocolate", "Hot chocolate for cold winter", "Cocoa powder, milk, honey, chilli",
                "Place the milk, cocoa powder in a saucepan and heat until warm.Add honey and chilli."));
        cookbook.put(cookbook.size() + 1, new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", "bread, butter, cheese, ketchup",
                "Spread butter on bread, put one slice of cheese and add ketchup on the top."));
    }

    @GetMapping("/all")
    public HashMap<Integer, Recipe> getAllRecipes() {
        return cookbook;
    }

    @GetMapping
    public Optional<Recipe> getRecipeById(@RequestParam Integer recipeId) {
        return cookbook.entrySet().stream().filter(element -> element.getKey().equals(recipeId)).map(Map.Entry::getValue).findFirst();
    }

    @PostMapping("/add")
    void addRecipe(@RequestBody Recipe recipe) {
        cookbook.put(cookbook.size() + 1, recipe);
    }
}