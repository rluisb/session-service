package com.github.rluisb.session.client;

import com.github.rluisb.session.domain.model.Agenda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "${agenda.service.name}", url = "${agenda.service.url}")
public interface AgendaRestClient {

    @RequestMapping("/agenda/{id}")
    Agenda getAgendaById(@PathVariable("id") String id);

}
