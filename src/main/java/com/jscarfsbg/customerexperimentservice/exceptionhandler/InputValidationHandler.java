package com.jscarfsbg.customerexperimentservice.exceptionhandler;

import com.jscarfsbg.customerexperimentservice.dto.InputFailedValidationResponse;
import com.jscarfsbg.customerexperimentservice.exception.InputFailedValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputFailedValidation.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputFailedValidation exception) {
        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
