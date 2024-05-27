package com.example.applicationformanagementnotes.exceptions;

public class NoteNotFoundException extends RuntimeException {

    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
