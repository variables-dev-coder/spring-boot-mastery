package com.munna.demo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(
	        org.springframework.web.bind.MethodArgumentNotValidException ex,
	        jakarta.servlet.http.HttpServletRequest request) {

	    Map<String, Object> error = new HashMap<>();

	    List<String> errors = ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(e -> e.getDefaultMessage())
	            .toList();

	    error.put("timestamp", LocalDateTime.now());
	    error.put("status", HttpStatus.BAD_REQUEST.value());
	    error.put("errors", errors);
	    error.put("path", request.getRequestURI());

	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", ex.getMessage());
        error.put("path", request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(
            Exception ex,
            HttpServletRequest request) {

        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Something went wrong");
        error.put("path", request.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}