package com.w2w.What2Watch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LanguageNotFoundException extends  Exception{
    public LanguageNotFoundException(String message){
        super(message);
    }
}
