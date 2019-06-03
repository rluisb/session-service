package com.github.rluisb.session.domain.model;

import io.swagger.annotations.ApiModel;

@ApiModel("VoteOption")
public enum VoteOption {

    SIM("SIM"),
    NAO("NAO");

    private String option;

    VoteOption(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
