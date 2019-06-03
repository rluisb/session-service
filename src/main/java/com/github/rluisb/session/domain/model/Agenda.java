package com.github.rluisb.session.domain.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Agenda implements Serializable {

    private String id;
    private String title;
    private String subject;
    private String description;
    @ApiModelProperty(
            dataType = "Enum",
            allowableValues = "WAITING_FOR_VOTING, VOTING_IN_PROGRESS, CLOSED_VOTE"
    )
    private AgendaStatus status;


    public Agenda() {
    }

    public Agenda(String id, String title, String subject, String description, AgendaStatus status) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }

    private Agenda(Agenda agenda, AgendaStatus newStatus) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.subject = agenda.getSubject();
        this.description = agenda.getDescription();
        this.status = newStatus;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public AgendaStatus getStatus() {
        return status;
    }

    public Agenda updateStatus(AgendaStatus agendaStatus) {
        return new Agenda(this, agendaStatus);
    }
}
