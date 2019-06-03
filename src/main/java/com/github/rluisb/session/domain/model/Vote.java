package com.github.rluisb.session.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.rluisb.session.api.dto.VoteDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Vote implements Serializable {

    private Associate associate;
    private VoteOption voteOption;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime occurrence;

    public Vote() {
    }

    private Vote(Associate associate, VoteOption voteOption) {
        this.associate = associate;
        this.voteOption = voteOption;
        this.occurrence = LocalDateTime.now();
    }

    public Associate getAssociate() {
        return associate;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public LocalDateTime getOccurrence() {
        return occurrence;
    }

    public String getAssociateId() {
        return this.associate.getId();
    }

    public Boolean isVoteForYes() {
        return this.voteOption.equals(VoteOption.SIM);
    }

    public Boolean isVoteForNo() {
        return this.voteOption.equals(VoteOption.NAO);
    }

    public static Vote buildFrom(VoteDto voteDto) {
        return new Vote(voteDto.getAssociate(), voteDto.getVoteOption());
    }
}
