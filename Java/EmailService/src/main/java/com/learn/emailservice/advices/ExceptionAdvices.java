package com.learn.emailservice.advices;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonException(JsonProcessingException ex, WebRequest webRequest){
        return ResponseEntity.status(500).body("Json Processing Exception: " + ex.getMessage());
    }
}
