package com.petprojects.articles.exception;

import org.springframework.http.HttpStatus;

public class ApiExceptionDTO {

    private final String message;
    private final HttpStatus status;
    private final int statusCode;

    public ApiExceptionDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        statusCode = status.value();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
