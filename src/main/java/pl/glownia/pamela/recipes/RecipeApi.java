package pl.glownia.pamela.recipes;

import org.springframework.web.bind.annotation.*;

@RestController
public class RecipeApi {

    private Recipe recipe;

    public RecipeApi() {
        recipe = new Recipe("Hot chocolate", "Hot chocolate for cold winter", "Cocoa powder, milk, honey, chilli flakes",
                "Place the milk, cocoa powder in a saucepan and heat until warm.Add honey and chilli flakes.");
    }

    @GetMapping("/api/recipe")
    public Recipe getRecipe() {
        return recipe;
    }

    @PostMapping("/api/recipe")
    void changeRecipe() {
        recipe = new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", "bread, butter, cheese, ketchup",
                "Spread butter on bread, put one slice of cheese and add ketchup on the top.");
    }
}