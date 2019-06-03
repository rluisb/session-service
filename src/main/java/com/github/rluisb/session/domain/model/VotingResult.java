package com.github.rluisb.session.domain.model;

import java.io.Serializable;
import java.util.Collection;

public class VotingResult implements Serializable {

    private Session session;
    private Long votesForYes;
    private Long votesForNo;

    public VotingResult() {
    }

    public VotingResult(Session session, Long votesForYes, Long votesForNo) {
        this.session = session;
        this.votesForYes = votesForYes;
        this.votesForNo = votesForNo;
    }

    public VotingResult(Session session) {
        this.session = session;
        this.votesForYes = countVotesForYes(session.getVotes());
        this.votesForNo = countVotesForNo(session.getVotes());
    }

    private Long countVotesForYes(Collection<Vote> votes) {
        return votes.stream()
                .filter(Vote::isVoteForYes)
                .count();
    }

    private Long countVotesForNo(Collection<Vote> votes) {
        return votes.stream()
                .filter(Vote::isVoteForNo)
                .count();
    }

    public Session getSession() {
        return session;
    }

    public Long getVotesForYes() {
        return votesForYes;
    }

    public Long getVotesForNo() {
        return votesForNo;
    }
}
