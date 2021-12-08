package pl.glownia.pamela.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    private List<Recipe> cookbook;

    public RecipeService() {
        cookbook = new ArrayList<>();
        cookbook.add(new Recipe("Tea with lemon and honey", "Classical tea for autumn evenings", new String[]{"water", "tea bag", "honey", "slice of lemon"},
                new String[]{"Boil the water", "put tea bag into the cup.", "After removing tea bag add honey and lemon."}));
        cookbook.add(new Recipe("Hot chocolate", "Hot chocolate for cold winter", new String[]{"Cocoa powder", "milk", "honey", "chilli"},
                new String[]{"Place the milk ", "cocoa powder in a saucepan ", "and heat until warm.", "Add honey and chilli."}));
        cookbook.add(new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", new String[]{"bread", "butter", "cheese", "ketchup"},
                new String[]{"Spread butter on bread ", "put one slice of cheese ", "add ketchup on the top."}));
    }

    public List<Recipe> getCookbook() {
        var finder = recipeRepository.findAll();
        finder.forEach(recipe -> cookbook.add(recipe));
        return cookbook;
    }

    public Optional<Recipe> getChosenRecipe(Long recipeId) {
        return Optional.ofNullable(cookbook.stream().filter(element -> element.getId().equals(recipeId)).findFirst().orElseThrow(() -> new RuntimeException("Recipe doesn't exist.")));
    }

    void addRecipe(Recipe recipe) {
        cookbook.add(recipe);
    }

    void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }
}