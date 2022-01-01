package pl.glownia.pamela.recipes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RecipeBadRequestAdvice {
    @ResponseBody
    @ExceptionHandler(RecipeBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException(RecipeBadRequestException exception) {
        return exception.getMessage();
    }
}
