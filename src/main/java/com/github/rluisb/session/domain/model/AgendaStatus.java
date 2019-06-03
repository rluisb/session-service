package com.github.rluisb.session.domain.model;

public enum AgendaStatus {

    WAITING_FOR_VOTING(1, "WAITING_FOR_VOTING"),
    VOTING_IN_PROGRESS(2, "VOTING_IN_PROGRESS"),
    CLOSED_VOTE(3, "CLOSED_VOTE");

    private Integer code;
    private String type;

    AgendaStatus(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
