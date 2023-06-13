package com.spade.oauth.exception;

/**
 * 인증 실패 Exception
 */
public class AuthorizeFailureException extends RuntimeException {
    public AuthorizeFailureException(String msg) {
        super(msg);
    }
}
