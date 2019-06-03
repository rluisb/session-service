package com.github.rluisb.session.service;

import com.github.rluisb.session.domain.model.Agenda;

import java.util.Optional;

public interface AgendaService {

    Optional<Agenda> getAgendaById(String agendaId);

}
