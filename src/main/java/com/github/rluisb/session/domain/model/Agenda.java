package com.github.rluisb.session.domain.model;

import java.io.Serializable;

public class Agenda implements Serializable {

    private String id;
    private String title;
    private String subject;
    private String description;


    public Agenda() {
    }

    public Agenda(String id, String title, String subject, String description) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.description = description;
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
}
