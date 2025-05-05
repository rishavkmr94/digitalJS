package com.learn.userauthentication.advices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learn.userauthentication.exceptions.InvalidPasswordException;
import com.learn.userauthentication.exceptions.UserAlreadyExistsException;
import com.learn.userauthentication.exceptions.UserDoesNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvices {
    private final Map<String,String> responseMap;
    public ExceptionAdvices() {
        responseMap = new HashMap<>();
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Map<String,String>> handleJsonProcessingException(JsonProcessingException ex, WebRequest webRequest){
        responseMap.put("message",ex.getMessage());
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest webRequest){
        responseMap.put("message",ex.getMessage());
        return new ResponseEntity<>(responseMap, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Map<String,String>> handleInvalidPasswordException(InvalidPasswordException ex,WebRequest webRequest){
        responseMap.put("message",ex.getMessage());
        return new ResponseEntity<>(responseMap, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Map<String,String>> handleUserDoesNotExistException(UserDoesNotExistException ex,WebRequest webRequest){
        responseMap.put("message",ex.getMessage());
        return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleException(Exception ex,WebRequest webRequest){
        responseMap.put("message",ex.getMessage());
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
