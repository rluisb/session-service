package com.github.rluisb.session.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.rluisb.session.domain.entity.SessionEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Session implements Serializable {

    private String id;
    private Agenda agenda;
    private Collection<Vote> votes;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endTime;
    private DurationTime duration;


    public Session() {
    }


    private Session(Agenda agenda, DurationTime duration) {
        this.agenda = agenda;
        this.votes = new ArrayList<>();
        this.startTime = generateStartDate();
        this.endTime = generateEndDateFromDuration(duration);
        this.duration = duration;
    }

    private Session(Agenda agenda) {
        this.agenda = agenda;
        this.votes = new ArrayList<>();
        this.startTime = generateStartDate();
        this.endTime = generateEndDateFromDuration(duration);
        this.duration = new DurationTime();
    }

    private Session(String id, Agenda agenda, Collection<Vote> votes, LocalDateTime startTime,
                    LocalDateTime endTime, DurationTime duration) {
        this.id = id;
        this.agenda = agenda;
        this.votes = votes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    private Session(Session session, Collection<Vote> updatedVotes) {
        this.id = session.getId();
        this.agenda = session.getAgenda();
        this.votes = updatedVotes;
        this.startTime = session.getStartTime();
        this.endTime = session.getEndTime();
        this.duration = session.getDuration();
    }

    private Session(Session session, Agenda updatedStatusAgenda) {
        this.id = session.getId();
        this.agenda = updatedStatusAgenda;
        this.votes = session.getVotes();
        this.startTime = session.getStartTime();
        this.endTime = session.getEndTime();
        this.duration = session.getDuration();
    }

    private LocalDateTime generateEndDateFromDuration(DurationTime duration) {
        return this.startTime.plus(duration.getTime(), duration.getTimeUnity());
    }

    private LocalDateTime generateStartDate() {
        return LocalDateTime.now();
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

    public Session addVote(Vote vote) {
        Collection<Vote> updatedVotes = Stream.of(vote)
                .collect(Collectors.toCollection(this::getVotes));

        return new Session(this, updatedVotes);
    }

    public Boolean canAssociateVote(Vote vote) {
        return this.votes.stream()
                .noneMatch(oldVote -> oldVote.getAssociateId().equals(vote.getAssociateId()));
    }

    public Boolean isOpenForVote() {
        return this.endTime.isAfter(LocalDateTime.now());
    }

    public static Session buildFrom(Agenda agenda, DurationTime duration) {
        if (Objects.isNull(duration)) {
            return new Session(agenda);
        }
        return new Session(agenda, duration);
    }

    public static Session buildFrom(SessionEntity sessionEntity) {
        return new Session(sessionEntity.getId(), sessionEntity.getAgenda(), sessionEntity.getVotes(),
                sessionEntity.getStartTime(), sessionEntity.getEndTime(), sessionEntity.getDuration());
    }
}
