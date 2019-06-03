package com.github.rluisb.session.domain.model;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

public class DurationTime implements Serializable {

    private static final Long DEFAULT_TIME = 1L;

    private Long time = DEFAULT_TIME;
    private ChronoUnit timeUnity = ChronoUnit.MINUTES;

    public DurationTime() {
    }

    public DurationTime(Long time, ChronoUnit timeUnity) {
        this.time = time;
        this.timeUnity = timeUnity;
    }

    public Long getTime() {
        return time;
    }

    public ChronoUnit getTimeUnity() {
        return timeUnity;
    }
}
