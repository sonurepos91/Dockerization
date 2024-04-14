package com.devops.dockerization.customException;

public class UserNotFoundException extends BaseException{


    public UserNotFoundException (String fieldName, String value, String message) {
        super(fieldName, value, message);
    }
}
