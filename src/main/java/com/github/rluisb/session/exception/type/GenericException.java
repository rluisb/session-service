package com.github.rluisb.session.exception.type;

public class GenericException extends Exception {

    private int code;
    private int httpCode = 500;

    public GenericException(final int code, final String error) {
        super(error);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(final int httpCode) {
        this.httpCode = httpCode;
    }

}

