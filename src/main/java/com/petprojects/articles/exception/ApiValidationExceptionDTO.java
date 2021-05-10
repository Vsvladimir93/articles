package com.petprojects.articles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ApiValidationExceptionDTO {

    private final String message;
    private final HttpStatus status;
    private final int statusCode;
    private final String objectName;
    private final int errorsCount;
    private final List<ApiFieldErrors> errors;

    public ApiValidationExceptionDTO(String message, HttpStatus status, BindingResult bindingResult) {
        this.message = message;
        this.status = status;
        statusCode = status.value();
        objectName = bindingResult.getObjectName();
        errorsCount = bindingResult.getErrorCount();
        errors = bindingResult
                .getFieldErrors()
                .stream()
                .map(e ->
                        new ApiFieldErrors(
                                e.getDefaultMessage(),
                                e.getField(),
                                e.getRejectedValue() != null ? e.getRejectedValue().toString() : "null"
                        )
                ).collect(Collectors.toUnmodifiableList());
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

    public String getObjectName() {
        return objectName;
    }

    public int getErrorsCount() {
        return errorsCount;
    }

    public List<ApiFieldErrors> getErrors() {
        return errors;
    }

    private class ApiFieldErrors {
        private final String message;
        private final String field;
        private final String rejectedValue;

        public ApiFieldErrors(String message, String field, String rejectedValue) {
            this.message = message;
            this.field = field;
            this.rejectedValue = rejectedValue;
        }

        public String getMessage() {
            return message;
        }

        public String getField() {
            return field;
        }

        public String getRejectedValue() {
            return rejectedValue;
        }

    }

}
