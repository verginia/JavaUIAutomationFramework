Feature: Login Flow Test Suite

  Background:
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" link is accessed

  Scenario Outline: An error message is displayed when trying to log in with invalid <attribute>
    And the following set of credentials is entered into the login form:
      | <email>    |
      | <password> |
    When "loginButton" from "LoginPage" is clicked
    Then the following list of error messages is displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | attribute                       | email           | password       |
      | email input data (not existent) | valid@gmail.com | wrong email    |
      | password input data             | valid@gmail.com | wrong password |

  Scenario Outline: A valid user is able to log into the system
    And the following set of credentials is entered into the login form:
      | <email>    |
      | <password> |
    When "loginButton" from "LoginPage" is clicked
    Then the current url contains the following keyword: "account"
    Examples:
      | email                        | password              |
      | xavier.considine@hotmail.com | jtbci5pwtjlaybmyhivw1 |

  Scenario Outline: A valid user is able to log into the system using generic step
    And the following fields from "LoginPage" are populated with data:
      | emailInput    | <email>    |
      | passwordInput | <password> |
    When "loginButton" from "LoginPage" is clicked
    Then the current url contains the following keyword: "account"
    Examples:
      | email                        | password              |
      | xavier.considine@hotmail.com | jtbci5pwtjlaybmyhivw1 |