package pl.glownia.pamela.recipes;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeManager {
    private final HashMap<Integer, Recipe> cookbook;

    public RecipeManager() {
        cookbook = new HashMap<>();
        cookbook.put(cookbook.size() + 1, new Recipe("Tea with lemon and honey", "Classical tea for autumn evenings", "water, tea bag, honey, slice of lemon ",
                "Boil the water, put tea bag into the cup. After removing tea bag, add honey and lemon."));
        cookbook.put(cookbook.size() + 1, new Recipe("Hot chocolate", "Hot chocolate for cold winter", "Cocoa powder, milk, honey, chilli",
                "Place the milk, cocoa powder in a saucepan and heat until warm.Add honey and chilli."));
        cookbook.put(cookbook.size() + 1, new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", "bread, butter, cheese, ketchup",
                "Spread butter on bread, put one slice of cheese and add ketchup on the top."));
    }

    public HashMap<Integer, Recipe> getCookbook() {
        return cookbook;
    }

    public Optional<Recipe> getChosenRecipe(Integer recipeId) {
        return cookbook.entrySet().stream().filter(element -> element.getKey().equals(recipeId)).map(Map.Entry::getValue).findFirst();
    }

    void addRecipe(Recipe recipe) {
        cookbook.put(cookbook.size() + 1, recipe);
    }

    void deleteRecipe(Integer recipeId) {
        cookbook.entrySet().removeIf(element -> element.getKey().equals(recipeId));
    }
}