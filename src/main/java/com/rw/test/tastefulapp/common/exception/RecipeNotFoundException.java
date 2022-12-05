package com.rw.test.tastefulapp.common.exception;

public class RecipeNotFoundException extends RuntimeException {
    public RecipeNotFoundException(String msg) {
        super(msg);
    }
}
