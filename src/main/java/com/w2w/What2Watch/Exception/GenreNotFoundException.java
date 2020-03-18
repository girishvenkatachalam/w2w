package com.w2w.What2Watch.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GenreNotFoundException  extends Exception {
    public GenreNotFoundException(String message) {
        super(message);
    }
}
