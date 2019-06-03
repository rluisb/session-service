package com.github.rluisb.session.exception.type;

public class AgendaAlreadyBeenVotedException extends GenericException {

    public AgendaAlreadyBeenVotedException() {
        super(3, "Agenda already voted in this agenda.");
    }
}
