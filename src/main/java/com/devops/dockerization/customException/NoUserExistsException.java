package com.devops.dockerization.customException;

public class NoUserExistsException extends BaseException{
    public NoUserExistsException (String fieldName, String value, String message) {
        super(fieldName, value, message);
    }
}
