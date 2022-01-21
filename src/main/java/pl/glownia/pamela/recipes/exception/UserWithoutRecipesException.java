package pl.glownia.pamela.recipes.exception;

public class UserWithoutRecipesException extends RuntimeException {
    public UserWithoutRecipesException() {
        super("You don't have any recipes.");
    }
}
