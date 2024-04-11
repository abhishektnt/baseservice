package com.gravity.fastcommerce.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex, HttpServletRequest request) {
        String apiPath = getRequestPath(request);
        logger.error("An error occurred in API '{}': {}", apiPath, ex.getMessage(), ex);
        CustomErrorResponse errorResponse = new CustomErrorResponse.Builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).errorMessage("An error occurred: " + ex.getMessage()).apiPath(apiPath).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        String apiPath = getRequestPath(request);
        logger.error("Resource not found in API '{}': {}", apiPath, e.getMessage(), e);
        CustomErrorResponse errorResponse = new CustomErrorResponse.Builder().statusCode(HttpStatus.NOT_FOUND.value()).errorMessage("Resource not found: " + e.getMessage()).apiPath(apiPath).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private String getRequestPath(HttpServletRequest request) {
        return Optional.of(((ServletWebRequest) request).getRequest().getRequestURI()).orElse("UNKNOWN_API");
    }
}