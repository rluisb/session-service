package com.github.rluisb.session.service.impl;

import com.github.rluisb.session.client.AgendaRestClient;
import com.github.rluisb.session.domain.model.Agenda;
import com.github.rluisb.session.service.AgendaService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AgendaServiceImpl implements AgendaService {

    private AgendaRestClient agendaRestClient;

    public AgendaServiceImpl(AgendaRestClient agendaRestClient) {
        this.agendaRestClient = agendaRestClient;
    }

    @Override
    public Optional<Agenda> getAgendaById(String agendaId) {
        return Stream.of(agendaId)
                .filter(Objects::nonNull)
                .map(agendaRestClient::getAgendaById)
                .findFirst();
    }
}
