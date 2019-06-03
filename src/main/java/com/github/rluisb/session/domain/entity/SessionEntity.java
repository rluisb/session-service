package com.github.rluisb.session.domain.entity;

import com.github.rluisb.session.domain.model.Agenda;
import com.github.rluisb.session.domain.model.DurationTime;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.Vote;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Document(collection = "sessions")
public class SessionEntity {

    @Id
    private String id;
    private Agenda agenda;
    private Collection<Vote> votes;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DurationTime duration;

    public SessionEntity() {
    }

    private SessionEntity(Agenda agenda, Collection<Vote> votes, LocalDateTime startTime,
                          LocalDateTime endTime, DurationTime duration) {
        this.agenda = agenda;
        this.votes = votes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    private SessionEntity(String id, Agenda agenda, Collection<Vote> votes, LocalDateTime startTime,
                          LocalDateTime endTime, DurationTime duration) {
        this.id = id;
        this.agenda = agenda;
        this.votes = votes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public DurationTime getDuration() {
        return duration;
    }

    public static SessionEntity buildFrom(Session session) {
        if (Objects.isNull(session.getId())) {
            return new SessionEntity(session.getAgenda(), session.getVotes(), session.getStartTime(),
                    session.getEndTime(), session.getDuration());
        }
        return new SessionEntity(session.getId(), session.getAgenda(), session.getVotes(), session.getStartTime(),
                session.getEndTime(), session.getDuration());
    }
}
