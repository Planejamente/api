package org.planejamente.planejamente.exception;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import jakarta.xml.bind.ValidationException;
import org.antlr.v4.runtime.InputMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleGlobalException(Exception ex) {
        return ResponseEntity.status(500).body(ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException ex) { return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage()); }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleInputMismatchException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(400).body(ex.getFieldError());
    }
    
    @ExceptionHandler(GoogleJsonResponseException.class)
    public ResponseEntity handleGoogleJsonResponseException(GoogleJsonResponseException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getMessage());
    }
}