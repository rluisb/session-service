package com.github.rluisb.session.service.impl;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.domain.entity.SessionEntity;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.Vote;
import com.github.rluisb.session.domain.model.VotingResult;
import com.github.rluisb.session.repository.SessionRepository;
import com.github.rluisb.session.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> openSession(SessionDto sessionDto) {
        return Stream.of(sessionDto)
                .map(Session::buildFrom)
                .map(SessionEntity::buildFrom)
                .map(sessionRepository::save)
                .map(Session::buildFrom)
                .findFirst();
    }

    @Override
    public Optional<Session> executeVote(String sessionId, VoteDto voteDto) {
        Vote newVote = Vote.buildFrom(voteDto);
        return Stream.of(sessionId)
                .filter(Objects::nonNull)
                .map(sessionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Session::buildFrom)
                .map(session -> session.addVote(newVote))
                .map(SessionEntity::buildFrom)
                .map(sessionRepository::save)
                .map(Session::buildFrom)
                .findFirst();
    }

    @Override
    public Optional<VotingResult> generateVotingResult(String sessionId) {
        return Stream.of(sessionId)
                .filter(Objects::nonNull)
                .map(sessionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Session::buildFrom)
                .map(VotingResult::new)
                .findFirst();
    }
}
