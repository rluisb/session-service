package com.github.rluisb.session.api.dto;

import com.github.rluisb.session.domain.model.DurationTime;

public class SessionDto {

    private String agendaId;
    private DurationTime durationTime;

    public SessionDto() {
    }

    public SessionDto(String agendaId) {
        this.agendaId = agendaId;
    }

    public SessionDto(String agendaId, DurationTime durationTime) {
        this.agendaId = agendaId;
        this.durationTime = durationTime;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public DurationTime getDurationTime() {
        return durationTime;
    }
}
