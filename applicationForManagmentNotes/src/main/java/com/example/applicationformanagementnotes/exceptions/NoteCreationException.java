package com.example.applicationformanagementnotes.exceptions;

public class NoteCreationException extends RuntimeException {

    public NoteCreationException(String message) {
        super(message);
    }

    public NoteCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
