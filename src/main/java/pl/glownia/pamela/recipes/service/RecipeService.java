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
}