package pl.glownia.pamela.recipes.exception;

public class RecipeBadRequestException extends RuntimeException {
    public RecipeBadRequestException() {
        super("Recipe with chosen feature doesn't exist.");
    }
}