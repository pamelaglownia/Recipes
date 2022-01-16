package pl.glownia.pamela.recipes.exception;

public class UserForbiddenException extends RuntimeException {

    public UserForbiddenException() {
        super("Only author can modify or delete this recipe.");
    }
}