package pl.glownia.pamela.recipes.exception;

public class RecipeBadRequestException extends RuntimeException {
    public RecipeBadRequestException() {
        super("Your request contains errors. Check it out and send it again, please.");
    }
}