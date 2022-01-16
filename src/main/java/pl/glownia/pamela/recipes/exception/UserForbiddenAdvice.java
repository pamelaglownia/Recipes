package pl.glownia.pamela.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserForbiddenAdvice {
    @ResponseBody
    @ExceptionHandler(UserForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)

    public String handleUserForbiddenException(UserForbiddenException exception) {
        return exception.getMessage();
    }
}