package com.github.rluisb.session.domain.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel("Associate")
public class Associate implements Serializable {

    private String id;
    private String name;

    public Associate() {
    }

    public Associate(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
