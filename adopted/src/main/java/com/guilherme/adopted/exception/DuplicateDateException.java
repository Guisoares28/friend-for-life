package com.guilherme.adopted.exception;

public class DuplicateDateException extends RuntimeException {

    public DuplicateDateException(String message){
        super(message);
    }
}
