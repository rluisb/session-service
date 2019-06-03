package com.github.rluisb.session.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String error;
    private List<String> errors;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public List<String> getErrors() {
        return errors;
    }
}
