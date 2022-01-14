package pl.glownia.pamela.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserUnauthorizedAdvice {
    @ResponseBody
    @ExceptionHandler(UserUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)

    public String handleUserUnauthorizedException(UserUnauthorizedException exception) {
        return exception.getMessage();
    }
}