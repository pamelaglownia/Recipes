package pl.glownia.pamela.recipes.exception;

public class UserUnauthorizedException extends RuntimeException {
    public UserUnauthorizedException() {
        super("Only author can modify or delete this recipe.");
    }
}