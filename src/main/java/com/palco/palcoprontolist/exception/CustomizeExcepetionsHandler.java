package com.palco.palcoprontolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomizeExcepetionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        var erros = exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).toList();
        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }

}
