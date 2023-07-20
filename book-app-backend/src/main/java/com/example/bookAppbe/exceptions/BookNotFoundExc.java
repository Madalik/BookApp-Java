package com.example.bookAppbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNotFoundExc extends RuntimeException{

    public BookNotFoundExc() {
        super();
    }
}
