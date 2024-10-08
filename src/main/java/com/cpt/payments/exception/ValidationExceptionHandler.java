package com.cpt.payments.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cpt.payments.pojo.ErrorResponse;

@ControllerAdvice
public class ValidationExceptionHandler {                                              //throw exception will come here

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {

   	ErrorResponse errorResponseDTO = new ErrorResponse(ex.getErrorCode(),ex.getMessage());
    	
        return new ResponseEntity<>(errorResponseDTO, ex.getHttpStatus());
    }
}