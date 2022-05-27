Feature: random number can be generated
  Scenario: client wants to generate a random number
    When the client calls /getRandomNumber
    Then the client receives status code of 200
    And the client receives a random number
  Scenario: client wants to generate a random number with an upperbound limit
    When the client calls /getRandomNumber with upperbound 354321
    Then the client receives status code of 200
    And the client receives a random number with upper bound of 354321
  Scenario: client wants to generate a random number with an unsupported upperbound limit
    When the client calls /getRandomNumber with upperbound 12
    Then the client receives status code of 400
    And the client receives an error message that the upperbound is not valid
