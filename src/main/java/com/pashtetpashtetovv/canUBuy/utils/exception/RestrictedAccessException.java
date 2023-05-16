package com.pashtetpashtetovv.canUBuy.utils.exception;

public class RestrictedAccessException extends RuntimeException{

    public RestrictedAccessException() {
    }

    public RestrictedAccessException(String message) {
        super(message);
    }

    public RestrictedAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestrictedAccessException(Throwable cause) {
        super(cause);
    }

    public RestrictedAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
