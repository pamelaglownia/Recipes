package pl.glownia.pamela.recipes.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(long recipeId) {
        super("Recipe with id " + recipeId + " doesn't exist.");
    }
}
