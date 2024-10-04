package com.cpt.payments.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ValidationException extends RuntimeException {

	private int errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    public ValidationException(int errorCode, String errorMessage, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}