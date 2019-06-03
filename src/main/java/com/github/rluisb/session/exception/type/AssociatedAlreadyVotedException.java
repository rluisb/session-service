package com.github.rluisb.session.exception.type;

public class AssociatedAlreadyVotedException extends GenericException {

    public AssociatedAlreadyVotedException() {
        super(1, "Associated already voted in this agenda.");
    }
}
