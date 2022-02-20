package pl.glownia.pamela.recipes.recipe;

import org.springframework.stereotype.Service;
import pl.glownia.pamela.recipes.exception.RecipeBadRequestException;
import pl.glownia.pamela.recipes.exception.RecipeNotFoundException;
import pl.glownia.pamela.recipes.exception.UserWithoutRecipesException;
import pl.glownia.pamela.recipes.user.User;
import pl.glownia.pamela.recipes.user.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeService(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public Recipe addRecipe(Recipe recipe) {
        User user = userService.getByEmail(userService.getCurrentUserEmail());
        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }

    public Recipe getChosenRecipe(long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(() -> new RecipeNotFoundException(recipeId));
    }

    public List<Recipe> getCookbook() {
        return recipeRepository.findAll();
    }

    @Transactional
    public Recipe updateRecipe(long recipeId, Recipe recipe) {
        Recipe modifyingRecipe = getChosenRecipe(recipeId);
        if (userService.isAuthorOfRecipe(recipe.getUser())) {
            modifyingRecipe.setName(recipe.getName());
            modifyingRecipe.setCategory(recipe.getCategory());
            modifyingRecipe.setModificationDate(recipe.getModificationDate());
            modifyingRecipe.setDescription(recipe.getDescription());
            modifyingRecipe.setIngredients(recipe.getIngredients());
            modifyingRecipe.setDirections(recipe.getDirections());
        }
        return recipeRepository.save(modifyingRecipe);
    }

    public void deleteRecipe(long id) {
        if (userService.isAuthorOfRecipe(recipeRepository.getById(id).getUser())) {
            recipeRepository.deleteById(id);
        }
    }

    public List<Recipe> findRecipeByCategory(String category) {
        List<Recipe> foundRecipes = recipeRepository.findByCategory(category);
        if (foundRecipes.isEmpty()) {
            throw new RecipeBadRequestException();
        }
        return foundRecipes;
    }

    public List<Recipe> findRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByPartOfName(name);
        if (foundRecipes.isEmpty()) {
            throw new RecipeBadRequestException();
        }
        return foundRecipes;
    }

    public Set<Recipe> findAllCurrentUsersRecipe() {
        User user = userService.getByEmail(userService.getCurrentUserEmail());
        Set<Recipe> foundRecipes = recipeRepository.findByUser(user);
        if (foundRecipes.isEmpty()) {
            throw new UserWithoutRecipesException();
        }
        return foundRecipes;
    }
}