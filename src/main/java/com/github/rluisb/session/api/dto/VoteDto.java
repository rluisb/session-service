package com.github.rluisb.session.api.dto;

import com.github.rluisb.session.domain.model.Associate;
import com.github.rluisb.session.domain.model.VoteOption;

public class VoteDto {

    private Associate associate;
    private VoteOption voteOption;

    public VoteDto() {
    }

    public VoteDto(Associate associate, VoteOption voteOption) {
        this.associate = associate;
        this.voteOption = voteOption;
    }

    public Associate getAssociate() {
        return associate;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

}
