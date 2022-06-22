package com.company.controller;

import com.company.exp.BadRequestException;
import com.company.exp.IOException;
import com.company.exp.ItemNotFoundException;
import com.company.exp.NoPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandlerController {

    @ExceptionHandler({BadRequestException.class, ItemNotFoundException.class,
            })
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity<String> handleException(NoPermissionException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleException(IOException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
