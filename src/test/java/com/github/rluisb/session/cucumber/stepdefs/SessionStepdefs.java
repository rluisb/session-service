package com.github.rluisb.session.cucumber.stepdefs;

import com.github.rluisb.session.TestConfig;
import com.github.rluisb.session.api.dto.SessionDto;
import com.github.rluisb.session.api.dto.VoteDto;
import com.github.rluisb.session.cucumber.World;
import com.github.rluisb.session.domain.model.*;
import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static com.github.rluisb.session.cucumber.stepdefs.SessionStepdefs.MockObjects.getAgendaByIdMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.*;

public class SessionStepdefs extends TestConfig implements En {

    @LocalServerPort
    private int port;
    @Autowired
    private World world;
    @Autowired
    private RestTemplate restTemplate;

    public SessionStepdefs() {
        Before(new String[]{"@CallAgendaService"}, () -> {
            world.wireMockServer = new WireMockServer(8089);
            world.wireMockServer.start();
            world.wireMockServer.stubFor(get(urlEqualTo("http://localhost:8080/agenda-service/v1/agendas/a1b2c3"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                            .withBody(getAgendaByIdMock())));
        });

        After(new String[]{"@CallAgendaService"}, () -> {
            world.wireMockServer.stop();
        });

        Given("^an agenda with id (.*)$", (String agendaId) -> {
            world.map.put("agendaId", agendaId);
        });
        Given("^a duration time of (.*) (.*)", (Long time, ChronoUnit timeUnity) -> {
            world.map.put("durationTime", new DurationTime(time, timeUnity));
        });
        Given("^a session id (.*)$", (String sessionId) -> {
            world.map.put("sessionId", sessionId);
        });
        Given("^an associate with id (.*)$", (String associateId) -> {
            world.map.put("associateId", associateId);
        });
        Given("^a name (.*)", (String associateName) -> {
            world.map.put("associateName", associateName);
        });
        Given("^a vote with option (.*)", (VoteOption voteOption) -> {
            world.map.put("voteOption", voteOption);
        });
        When("^open a new session$", () -> {
            world.status = 200;

            String agendaId = (String) world.map.get("agendaId");
            DurationTime durationTime = (DurationTime) world.map.get("durationTime");
            SessionDto sessionDto = new SessionDto(agendaId, durationTime);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<SessionDto> entity = new HttpEntity<>(sessionDto, headers);

            try {
                world.session = restTemplate.exchange(String.format("http://localhost:%s/session-service/v1/sessions", port),
                        HttpMethod.POST,
                        entity, Session.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^execute the vote$", () -> {
            world.status = 200;

            String sessionId = (String) world.map.get("sessionId");
            String associateId = (String) world.map.get("associateId");
            String associateName = (String) world.map.get("associateName");
            Associate associate = new Associate(associateId, associateName);
            VoteOption voteOption = (VoteOption) world.map.get("voteOption");
            VoteDto voteDto = new VoteDto(associate, voteOption);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<VoteDto> entity = new HttpEntity<>(voteDto, headers);

            try {
                world.session = restTemplate.exchange(String.format("http://localhost:%s/session-service/v1/sessions/%s/vote", port, sessionId),
                        HttpMethod.PATCH,
                        entity, Session.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^request a voting result$", () -> {
            world.status = 200;

            String sessionId = (String) world.map.get("sessionId");

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            try {
                world.votingResult = restTemplate.exchange(
                        String.format("http://localhost:%s/session-service/v1/sessions/%s/result", port, sessionId),
                        HttpMethod.GET,
                        entity, VotingResult.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        Then("^should return status (\\d+)$", (Integer status) -> {
            assertEquals(status, world.status);
        });
        Then("^a new session$", () -> {
            assertNotNull(world.session);
        });
        Then("^a valid id for session$", () -> {
            assertNotNull(world.session.getId());
            assertFalse(world.session.getId().isEmpty());
        });
        Then("^an agenda with id (.*) for session$", (String agendaId) -> {
            assertEquals(agendaId, world.session.getAgenda().getId());
        });
        Then("^a start time$", () -> {
            assertNotNull(world.session.getStartTime());
        });
        Then("^an end time that is (.*) (.*) later than start date$", (Long time, ChronoUnit timeUnity) -> {
            LocalDateTime expectedEndTime = world.session.getStartTime().plus(time, timeUnity);
            assertEquals(expectedEndTime, world.session.getEndTime());

        });
        Then("^a message (.*)$", (String message) -> {
            assertTrue(world.errorMessage.contains(message));
        });
        Then("^a session with id (.*)$", (String sessionId) -> {
            assertEquals(sessionId, world.session.getId());
        });
        Then("^a list of votes$", () -> {
            assertNotNull(world.session.getVotes().isEmpty());
        });
        Then("^a voting result containing a session with id (.*)$", (String sessionId) -> {
            assertEquals(sessionId, world.votingResult.getSession().getId());
        });
        Then("^a list of votes for no$", () -> {
            assertNotNull(world.votingResult.getVotesForNo());
        });
        Then("^a list of votes for yes", () -> {
            assertNotNull(world.votingResult.getVotesForYes());
        });


    }

    static class MockObjects {
        public static String getAgendaByIdMock() {
            return "{\n" +
                    "    \"id\": \"a1b2c3\",\n" +
                    "    \"title\": \"Pauta 1\",\n" +
                    "    \"subject\": \"assunto pauta 1\",\n" +
                    "    \"description\": \"Descrição pauta 1\"\n" +
                    "  }";
        }
    }
}
