package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

import java.util.*;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    private List<Recipe> cookbook = new ArrayList<>();

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> getCookbook() {
        var finder = recipeRepository.findAll();
        finder.forEach(recipe -> cookbook.add(recipe));
        return cookbook;
    }

    public Optional<Recipe> getChosenRecipe(long recipeId) {
        return Optional.ofNullable(cookbook.stream().filter(element -> element.getId() == (recipeId)).findFirst().orElseThrow(() -> new RuntimeException("Recipe doesn't exist.")));
    }

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @EventListener(ApplicationReadyEvent.class)
    void fillDatabase() {
        addRecipe(new Recipe("Tea with lemon and honey", "Classical tea for autumn evenings", "water, tea bag, honey, slice of lemon",
                "Boil the water, put tea bag into the cup. After removing tea bag add honey and lemon."));
        addRecipe(new Recipe("Hot chocolate", "Hot chocolate for cold winter", "Cocoa powder, milk, honey, chilli",
                "Place the milk, cocoa powder in a saucepan, and heat until warm. Add honey and chilli."));
        addRecipe(new Recipe("Cheese sandwich with ketchup", "Simple sandwich for student", "bread, butter, cheese, ketchup",
                "Spread butter on bread, put one slice of cheese, add ketchup on the top."));
    }
}