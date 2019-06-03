package com.github.rluisb.session.api.dto;

import com.github.rluisb.session.domain.model.DurationTime;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class SessionDto {

    @NotNull(message = "The agenda id can't be null.")
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
