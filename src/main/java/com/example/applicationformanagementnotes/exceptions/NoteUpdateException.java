package com.example.applicationformanagementnotes.exceptions;

public class NoteUpdateException extends RuntimeException{

    public NoteUpdateException(String message) {
        super(message);
    }

    public NoteUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
