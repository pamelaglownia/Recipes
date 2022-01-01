package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.exception.RecipeBadRequestException;
import pl.glownia.pamela.recipes.exception.RecipeNotFoundException;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getChosenRecipe(long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException(id));
    }

    public List<Recipe> getCookbook() {
        return recipeRepository.findAll();
    }

    public void deleteRecipe(long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> findRecipeByCategory(String category) {
        List<Recipe> foundRecipes = recipeRepository.findByCategoryIgnoreCaseOrderByCreationDateDesc(category);
        if (foundRecipes.isEmpty()) {
            throw new RecipeBadRequestException();
        }
        return foundRecipes;
    }

    public List<Recipe> findRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByNameContainingIgnoreCaseOrderByCreationDateDesc(name);
        if (foundRecipes.isEmpty()) {
            throw new RecipeBadRequestException();
        }
        return foundRecipes;
    }
}