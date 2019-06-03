package com.github.rluisb.session.service;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.VotingResult;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    Optional<Session> openSession(SessionDto sessionDto);

    Optional<Session> executeVote(String sessionId, VoteDto vote);

    Optional<VotingResult> generateVotingResult(String sessionId);

    List<Session> getAllSessions();

}
