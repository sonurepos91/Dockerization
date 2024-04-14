package com.devops.dockerization.exception;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR("internal.server.error"),
    BAD_REQUEST("bad.request"),

    NOT_FOUND("not.found");
    private String code;

    ErrorCode (String code) {
        this.code = code;
    }

    public String getCode () {
        return code;
    }

}
