package com.openpayd.rate.enums;

public enum RateError {

    INVALID_CURRENCY_CODE(202, " You have provided one or more invalid Currency Codes. [Required format: currencies=EUR,USD,GBP,...]");

    private final int code;
    private final String message;

    RateError(int code, String messages) {
        this.code = code;
        this.message = messages;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
