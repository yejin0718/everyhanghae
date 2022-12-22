package com.everyhanghae.security.jwt.exception.custom;

public class NotValidJwtException extends RuntimeException{

    public NotValidJwtException(String message) {
        super(message);
    }

}
