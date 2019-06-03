Feature: Mantain sessions.

  Scenario: Open a voting session with success without set a duration time
    Given an agenda id a1b2c3
    When open a new session
    Then should return status 200
    And a new session
    And a valid id for session
    And an agenda with id a1b2c3
    And a start time
    And an end time that is 1 minute later than start date

  Scenario: Open a voting session with success setting a duration time
    Given an agenda id a1b2c3
    And a duration time of 3 minutes
    When open a new session
    Then should return status 200
    And a new session
    And a valid id for session
    And an agenda with id a1b2c3
    And a start time
    And an end time that is 3 minutes later than start date

  Scenario: Open a voting session with error when try to put a voted agenda for a new session
    Given an agenda id a1b2c3
    And a duration time of 3 minutes
    When open a new session
    Then should return status 406
    And a message Agenda already voted in this agenda.

  Scenario: Execute a vote for a session with success
    Given a session id c4b1b2 
    And an associate with id 123
    And a name Ricardo
    And a vote with option SIM
    When execute the vote
    Then should return status 200
    And a session with id c4b1b2
    And a list of votes

  Scenario: Execute a vote for a session with error when try to vote to a closed session
    Given a session id c4b1b2
    And an associate with id 123
    And a name Ricardo
    And a vote with option SIM
    When execute the vote
    Then should return status 406
    And a message This session already has been closed.

  Scenario: Execute a vote for a session with error when an associate try to vote a twice in same session
    Given a session id c4b1b2
    And an associate with id 123
    And a name Ricardo
    And a vote with option SIM
    When execute the vote
    Then should return status 406
    And a message Associated already voted in this agenda.

  Scenario: Get voting result with success
    Given a session id c4b1b2
    When request a voting result
    Then should return status 200
    And a voting result containing a session with id c4b1b2
    And a list of votes for yes
    And a list of votes for no

  Scenario: Get voting result with error when session already not exists
    Given a session id c4b1b2
    When request a voting result
    Then should return status 406
    And a message This session already not exists.