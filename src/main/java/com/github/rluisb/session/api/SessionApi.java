package com.github.rluisb.session.api;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.VotingResult;
import com.github.rluisb.session.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class SessionApi {

    private SessionService sessionService;

    public SessionApi(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/sessions")
    public Optional<ResponseEntity<Session>> openVotingSession(@RequestBody SessionDto sessionDto) {
        return Stream.of(sessionDto)
                .filter(Objects::nonNull)
                .map(sessionService::openSession)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst();
    }

    @PatchMapping("/sessions/{id}")
    public Optional<ResponseEntity<Session>> executeVoteForSession(@PathVariable("id") String sessionId,
                                                                   @RequestBody VoteDto voteDto) {
        return Stream.of(sessionService.executeVote(sessionId, voteDto))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst();
    }

    @GetMapping("/sessions/{id}")
    public Optional<ResponseEntity<VotingResult>> getVotingResultForSession(@PathVariable("id") String sessionId) {
        return Stream.of(sessionService.generateVotingResult(sessionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst();
    }
}
