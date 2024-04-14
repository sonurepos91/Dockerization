package com.devops.dockerization.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private String code;
    private String message;
    private List<ValidationFieldError> validationFieldErrorList = new ArrayList<ValidationFieldError>();
}
