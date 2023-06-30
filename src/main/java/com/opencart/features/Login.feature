Feature: Login Flow Test Suite


  Scenario Outline: An error message is displayed when trying to log in with invalid <attribute>
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" link is accessed
    And the following set of credentials is entered into the login form:
      | <email>    |
      | <password> |
    When login button is clicked
    Then the following list of error messages is displayed:
      | Warning: No match for E-Mail Address and/or Password. |
    Examples:
      | attribute                       | email           | password       |
      | email input data (not existent) | valid@gmail.com | wrong email    |
      | password input data             | valid@gmail.com | wrong password |


  Scenario Outline: A valid user is able to log into the system
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" link is accessed
    And the following set of credentials is entered into the login form:
      | <email>    |
      | <password> |
    When login button is clicked
    Then the current url contains the following keyword: "account"
    Examples:
      | email | password |
    |  xavier.considine@hotmail.com     |  jtbci5pwtjlaybmyhivw1        |