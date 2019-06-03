package com.github.rluisb.session.client;

import com.github.rluisb.session.domain.model.Agenda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Component
public class AgendaRestClient {

    @Value("${agenda.service.url}")
    private String agendaServiceUrl;
    private RestTemplate restTemplate;

    public AgendaRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Agenda getAgendaById(String agendaId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(agendaServiceUrl)
                        .path("/agendas")
                        .path("/")
                        .path(agendaId);

        return restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, Agenda.class)
                .getBody();
    }


}
