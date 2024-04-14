package com.devops.dockerization.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationFieldError {

    private String field;
    private String code;
    private String message;


}
