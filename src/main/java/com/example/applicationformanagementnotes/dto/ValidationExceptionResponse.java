package com.example.applicationformanagementnotes.dto;

public record ValidationExceptionResponse(
        String errorCode,
        String errorMessage
) {
    public ValidationExceptionResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}