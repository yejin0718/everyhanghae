package com.everyhanghae.shared.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {

    private int status;
    private String msg;

    public ExceptionResponse(ExceptionMessage exceptionMessage) {
        this.status = exceptionMessage.getStatus();
        this.msg = exceptionMessage.getMsg();
    }

    public ExceptionResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
