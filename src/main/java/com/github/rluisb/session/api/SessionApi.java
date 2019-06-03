package com.github.rluisb.session.api;

import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.exception.type.AgendaAlreadyBeenVotedException;
import com.github.rluisb.session.exception.type.AssociatedAlreadyVotedException;
import com.github.rluisb.session.exception.type.SessionHasEndedException;
import com.github.rluisb.session.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> openVotingSession(@Valid @RequestBody SessionDto sessionDto)
            throws AgendaAlreadyBeenVotedException {
        return Stream.of(sessionService.openSession(sessionDto))
                .filter(Objects::nonNull)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @PatchMapping("/sessions/{id}/vote")
    public ResponseEntity<?> executeVoteForSession(@PathVariable("id") String sessionId,
                                                   @Valid @RequestBody VoteDto voteDto)
            throws AssociatedAlreadyVotedException, SessionHasEndedException {
        return Stream.of(sessionService.executeVote(sessionId, voteDto))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<?> getVotingResultForSession(@PathVariable("id") String sessionId) {
        return Stream.of(sessionService.generateVotingResult(sessionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @GetMapping("/sessions")
    public ResponseEntity<?> getAllSessions() {
        return Stream.of(sessionService.getAllSessions())
                .filter(Objects::nonNull)
                .filter(sessions -> !sessions.isEmpty())
                .findFirst()
                .map(ResponseEntity::ok)
                .get();
    }
}
