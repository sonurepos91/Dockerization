package com.devops.dockerization.customException;

public class UserAlreadyExistsException extends BaseException{

    public UserAlreadyExistsException (String fieldName, String value, String message) {
        super(fieldName, value, message);
    }
}
