package com.github.rluisb.session.service.impl;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.domain.entity.SessionEntity;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.Vote;
import com.github.rluisb.session.domain.model.VotingResult;
import com.github.rluisb.session.exception.type.AgendaAlreadyBeenVotedException;
import com.github.rluisb.session.exception.type.AssociatedAlreadyVotedException;
import com.github.rluisb.session.exception.type.SessionHasEndedException;
import com.github.rluisb.session.repository.SessionRepository;
import com.github.rluisb.session.service.AgendaService;
import com.github.rluisb.session.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SessionServiceImpl implements SessionService {

    private SessionRepository sessionRepository;
    private AgendaService agendaService;

    public SessionServiceImpl(SessionRepository sessionRepository, AgendaService agendaService) {
        this.sessionRepository = sessionRepository;
        this.agendaService = agendaService;
    }

    @Override
    public Optional<Session> openSession(SessionDto sessionDto) throws AgendaAlreadyBeenVotedException {
        Stream.of(sessionDto)
                .filter(Objects::nonNull)
                .map(SessionDto::getAgendaId)
                .map(sessionRepository::findByAgenda_Id)
                .findFirst()
                .orElseThrow(AgendaAlreadyBeenVotedException::new);

        return Stream.of(agendaService.getAgendaById(sessionDto.getAgendaId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(agenda -> Session.buildFrom(agenda, sessionDto.getDurationTime()))
                .map(SessionEntity::buildFrom)
                .map(sessionRepository::save)
                .map(Session::buildFrom)
                .findFirst();
    }

    @Override
    public Optional<Session> executeVote(String sessionId, VoteDto voteDto) throws AssociatedAlreadyVotedException, SessionHasEndedException {
        Vote newVote = Vote.buildFrom(voteDto);

        Optional<Session> session = Stream.of(sessionId)
                .filter(Objects::nonNull)
                .map(sessionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Session::buildFrom)
                .findFirst();

        Stream.of(session)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(sessionForUpdate -> !sessionForUpdate.isOpenForVote())
                .findFirst()
                .orElseThrow(SessionHasEndedException::new);

        Stream.of(session)
                .map(Optional::get)
                .filter(sessionForUpdate -> sessionForUpdate.canAssociateVote(newVote))
                .findFirst()
                .orElseThrow(AssociatedAlreadyVotedException::new);

        return Stream.of(session)
                .map(Optional::get)
                .map(sessionForUpdate -> sessionForUpdate.addVote(newVote))
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

    @Override
    public List<Session> getAllSessions() {
        return Stream.of(sessionRepository.findAll())
                .filter(Objects::nonNull)
                .filter(sessions -> !sessions.isEmpty())
                .flatMap(Collection::parallelStream)
                .map(Session::buildFrom)
                .collect(Collectors.toList());
    }

}
