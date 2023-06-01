package com.spade.oauth.exception;

public class AuthorizeFailureException extends RuntimeException {
    public AuthorizeFailureException(String msg) {
        super(msg);
    }
}
