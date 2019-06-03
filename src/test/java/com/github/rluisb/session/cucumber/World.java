package com.github.rluisb.session.cucumber;

import com.github.rluisb.session.domain.model.Agenda;
import com.github.rluisb.session.domain.model.Session;
import com.github.rluisb.session.domain.model.VotingResult;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("cucumber-glue")
public class World {

    public Map<String, Object> map = Maps.newHashMap();
    public WireMockServer wireMockServer;
    public Integer status;
    public String errorMessage;
    public Agenda agenda;
    public Session session;
    public VotingResult votingResult;
}
