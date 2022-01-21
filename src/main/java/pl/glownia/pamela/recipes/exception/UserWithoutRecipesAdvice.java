package pl.glownia.pamela.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserWithoutRecipesAdvice {
    @ResponseBody
    @ExceptionHandler(UserWithoutRecipesException.class)
    @ResponseStatus(HttpStatus.OK)

    public String handleUserWithoutRecipesException(UserWithoutRecipesException exception) {
        return exception.getMessage();
    }
}