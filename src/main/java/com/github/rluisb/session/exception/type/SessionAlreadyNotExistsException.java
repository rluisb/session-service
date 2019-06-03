package com.github.rluisb.session.exception.type;

public class SessionAlreadyNotExistsException extends GenericException {

    public SessionAlreadyNotExistsException() {
        super(4, "This session already not exists.");
    }
}
