package com.devops.dockerization.customException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException{

    private String fieldName;
    private String value;
    private String message;
}
