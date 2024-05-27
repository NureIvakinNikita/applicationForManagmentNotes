package com.example.applicationformanagementnotes.exceptions;

public class NoteDeletionException extends RuntimeException{

    public NoteDeletionException(String message) {
        super(message);
    }

    public NoteDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
