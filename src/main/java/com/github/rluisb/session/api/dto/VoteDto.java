package com.github.rluisb.session.api.dto;

import com.github.rluisb.session.domain.model.Associate;
import com.github.rluisb.session.domain.model.VoteOption;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class VoteDto {

    @NotNull(message = "The associate can't be null.")
    private Associate associate;
    @NotNull(message = "The vote can't be null.")
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
