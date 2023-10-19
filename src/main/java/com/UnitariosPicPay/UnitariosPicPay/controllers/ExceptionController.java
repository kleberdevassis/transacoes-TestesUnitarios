package com.UnitariosPicPay.UnitariosPicPay.controllers;

import com.UnitariosPicPay.UnitariosPicPay.DTOs.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateUser(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("User Duplicated","400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFoundUser(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralizeError(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "404");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
