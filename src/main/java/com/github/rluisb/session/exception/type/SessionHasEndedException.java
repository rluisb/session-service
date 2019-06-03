package com.github.rluisb.session.exception.type;

public class SessionHasEndedException extends GenericException {

    public SessionHasEndedException() {
        super(2, "This session already has been closed.");
    }
}
