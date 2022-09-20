package ru.math.spb.calculator.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

//@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = { IncorrectExpressionException.class,
//            IllegalArgumentException.class })
    protected ResponseEntity handleConflict(RuntimeException ex,
                                            WebRequest request) {
        String bodyOfResponse = "Error, bitch!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
                HttpStatus.CONFLICT, request);
    }
}
