package com.rw.test.tastefulapp.api;

import com.rw.test.tastefulapp.common.api.ProblemResponse;
import com.rw.test.tastefulapp.common.exception.InvalidRequestException;
import com.rw.test.tastefulapp.common.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemResponse handleInvalidRequest(InvalidRequestException x) {
        return new ProblemResponse(HttpStatus.BAD_REQUEST.name(), "Invalid request", x.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ProblemResponse handleRecipeNotFound(RecipeNotFoundException x) {
        return new ProblemResponse(HttpStatus.NOT_FOUND.name(), "Recipe not found", x.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
