package com.jscarfsbg.customerexperimentservice.exception;

import lombok.Getter;

@Getter
public class InputFailedValidation extends RuntimeException {
    private final int errorCode;

    public InputFailedValidation(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
