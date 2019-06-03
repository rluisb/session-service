package com.github.rluisb.session.cucumber.stepdefs;

import com.github.rluisb.session.domain.model.VoteOption;
import cucumber.api.java8.En;

import java.time.temporal.ChronoUnit;

public class SessionStepdefs implements En {
    public SessionStepdefs() {
        Given("^an agenda id (.*)$", (String agendaId) -> {
        });
        Given("^a duration time of (.*) (.*)", (Long time, ChronoUnit timeUnity) -> {
        });
        Given("^a session id (.*)$", (String sessionId) -> {
        });
        Given("^an associate with id (.*)$", (String associateId) -> {
        });
        Given("^a name (.*)", (String associateName) -> {
        });
        Given("^a vote with option (.*)", (VoteOption voteOption) -> {
        });
        When("^open a new session$", () -> {
        });
        When("^execute the vote$", () -> {
        });
        When("^request a voting result$", () -> {
        });
        Then("^should return status (\\d+)$", (Integer status) -> {
        });
        Then("^a new session$", () -> {
        });
        Then("^a valid id for session$", () -> {
        });
        Then("^an agenda with id (.*)$", (String agendaId) -> {
        });
        Then("^a start time$", () -> {
        });
        Then("^an end time that is (.*) (.*) later than start date$", (Long time, ChronoUnit timeUnity) -> {
        });
        Then("^a message (.*)$", (String message) -> {
        });
        Then("^a session with id (.*)$", (String sessionId) -> {
        });
        Then("^a list of votes$", () -> {
        });
        Then("^a voting result containing a session with id (.*)$", (String sessionId) -> {
        });
        Then("^a list of votes for (.*)$", (VoteOption voteOption) -> {
        });


    }
}
