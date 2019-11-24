package com.openpayd.rate.exception;

import com.openpayd.rate.enums.RateError;

public class RateException extends RuntimeException {

    private int code;
    private String message;

    public RateException(RateError rateError) {
        super(rateError.getMessage());
        this.code = rateError.getCode();
        this.message = rateError.getMessage();
    }

    public RateException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
