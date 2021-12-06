package com.example11.demo.model.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String username) {
        super(String.format("%s", username));

    }
}
