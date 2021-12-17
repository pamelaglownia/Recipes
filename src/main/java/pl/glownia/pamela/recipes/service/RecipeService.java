package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getChosenRecipe(long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe doesn't exist."));
    }

    public Iterable<Recipe> getCookbook() {
        return recipeRepository.findAll();
    }

    public void deleteRecipe(long id) {
        recipeRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    void fillDatabase() {
        addRecipe(new Recipe("Tea with lemon and honey", "Classical tea for autumn evenings", "water, tea bag, honey, slice of lemon",
                "Boil the water, put tea bag into the cup. Pour water. After removing tea bag add honey and lemon."));
        addRecipe(new Recipe("Hot chocolate", "Hot chocolate for cold winter", "Cocoa powder, milk, honey, chilli",
                "Place the milk, cocoa powder in a saucepan, and heat until warm. Add honey and chilli."));
        addRecipe(new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", "bread, butter, cheese, ketchup",
                "Spread butter on bread, put one slice of cheese, add ketchup on the top."));
    }
}