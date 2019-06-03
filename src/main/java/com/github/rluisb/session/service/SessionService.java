package com.github.rluisb.session.service;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.VotingResult;
import com.github.rluisb.session.exception.type.AgendaAlreadyBeenVotedException;
import com.github.rluisb.session.exception.type.AssociatedAlreadyVotedException;
import com.github.rluisb.session.exception.type.SessionHasEndedException;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    Optional<Session> openSession(SessionDto sessionDto) throws AgendaAlreadyBeenVotedException;

    Optional<Session> executeVote(String sessionId, VoteDto vote) throws AssociatedAlreadyVotedException, SessionHasEndedException;

    Optional<Session> findSessionByAgendaId(String agendaId);

    Optional<VotingResult> generateVotingResult(String sessionId);

    List<Session> getAllSessions();

}
