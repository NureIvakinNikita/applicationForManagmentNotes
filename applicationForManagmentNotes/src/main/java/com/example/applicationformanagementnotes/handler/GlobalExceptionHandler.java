package com.example.applicationformanagementnotes.handler;

import com.example.applicationformanagementnotes.dto.NoteErrorResponse;
import com.example.applicationformanagementnotes.dto.ValidationExceptionResponse;
import com.example.applicationformanagementnotes.exceptions.NoteCreationException;
import com.example.applicationformanagementnotes.exceptions.NoteDeletionException;
import com.example.applicationformanagementnotes.exceptions.NoteNotFoundException;
import com.example.applicationformanagementnotes.exceptions.NoteUpdateException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<NoteErrorResponse> handleNoteNotFoundException(NoteNotFoundException ex) {
        NoteErrorResponse error = new NoteErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoteUpdateException.class)
    public ResponseEntity<NoteErrorResponse> handleNoteNoteUpdateException(NoteUpdateException ex) {
        NoteErrorResponse error = new NoteErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoteCreationException.class)
    public ResponseEntity<NoteErrorResponse> handleNoteNoteCreationException(NoteCreationException ex) {
        NoteErrorResponse error = new NoteErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoteDeletionException.class)
    public ResponseEntity<NoteErrorResponse> handleNoteNoteCreationException(NoteDeletionException ex) {
        NoteErrorResponse error = new NoteErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               @NotNull HttpHeaders headers,
                                                               @NotNull HttpStatusCode status,
                                                               @NotNull WebRequest webRequest) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError -> FieldError.getField() + ": " + FieldError.getDefaultMessage())
                .toList();
        var exceptionBody = new ValidationExceptionResponse("validation_exceptions", errors.toString());
        return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, webRequest);
    }
}
