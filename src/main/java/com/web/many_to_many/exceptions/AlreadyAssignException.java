package com.web.many_to_many.exceptions;

public class AlreadyAssignException extends RuntimeException {

    public AlreadyAssignException(String message) {
        super(message);
    }
}
