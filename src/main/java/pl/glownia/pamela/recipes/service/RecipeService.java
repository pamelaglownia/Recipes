package pl.glownia.pamela.recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.exception.RecipeBadRequestException;
import pl.glownia.pamela.recipes.exception.RecipeNotFoundException;
import pl.glownia.pamela.recipes.model.Recipe;
import pl.glownia.pamela.recipes.model.User;
import pl.glownia.pamela.recipes.repository.RecipeRepository;

import java.security.Principal;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserService userService;

    public Recipe addRecipe(Recipe recipe, Principal principal) {
        User user = userService.getByEmail(userService.getCurrentUser(principal));
        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }

    public Recipe getChosenRecipe(long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(recipeId));
    }

    public List<Recipe> getCookbook() {
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(long recipeId, Recipe recipe, Principal principal) {
        Recipe modifyingRecipe = getChosenRecipe(recipeId);
        if (userService.isAuthorOfRecipe(recipe.getUser(), principal)) {
            modifyingRecipe.setName(recipe.getName());
            modifyingRecipe.setCategory(recipe.getCategory());
            modifyingRecipe.setModificationDate(recipe.getModificationDate());
            modifyingRecipe.setDescription(recipe.getDescription());
            modifyingRecipe.setIngredients(recipe.getIngredients());
            modifyingRecipe.setDirections(recipe.getDirections());
        }
        return recipeRepository.save(modifyingRecipe);
    }

    public void deleteRecipe(long id, Principal principal) {
        if (userService.isAuthorOfRecipe(recipeRepository.getById(id).getUser(), principal)) {
            recipeRepository.deleteById(id);
        }
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