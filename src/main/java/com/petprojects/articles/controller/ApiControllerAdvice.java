package com.petprojects.articles.controller;

import com.petprojects.articles.exception.ApiException;
import com.petprojects.articles.exception.ApiExceptionDTO;
import com.petprojects.articles.exception.ApiValidationExceptionDTO;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApiControllerAdvice {

    private final Logger logger;

    public ApiControllerAdvice(final Logger logger) {
        this.logger = logger;
    }

    @ExceptionHandler
    public ResponseEntity<ApiExceptionDTO> handleApiException(ApiException e) {

        ApiExceptionDTO dto = new ApiExceptionDTO(e.getMessage(), e.getStatus());

        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiExceptionDTO> handleApiException(DataIntegrityViolationException e) {

        String message = "The data in the request caused a conflict. " +
                "Please try to change the data.";

        ApiExceptionDTO dto = new ApiExceptionDTO(message, HttpStatus.CONFLICT);

        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ApiExceptionDTO> handleApiException() {

        String message = "Bad request format. " +
                "Please try to change request data format.";

        ApiExceptionDTO dto = new ApiExceptionDTO(message, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiValidationExceptionDTO> handleApiException(MethodArgumentNotValidException e) {

        String message = "Validation error";

        ApiValidationExceptionDTO dto = new ApiValidationExceptionDTO(
                message,
                HttpStatus.BAD_REQUEST,
                e.getBindingResult()
        );

        return new ResponseEntity<>(dto, dto.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiExceptionDTO> handleApiException(Exception e) {

        String message = "Internal server error.";

        ApiExceptionDTO dto = new ApiExceptionDTO(message,
                HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(dto, dto.getStatus());
    }

}
