package com.petprojects.articles.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final HttpStatus status;
    private final int statusCode;

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        statusCode = status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
