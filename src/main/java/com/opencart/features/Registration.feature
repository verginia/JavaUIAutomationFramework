Feature: Register flow Feature Test Suite

  Background:
    Given The "https://andreisecuqa.host/" link is accessed
    And RegisterPage is accessed from HomePage menu

  Scenario: Access the Account Page after successful registration
    When the registration form is completed with valid random data
    And "privacyToggle" from "RegisterPage" is clicked
    And "continueButton" from "RegisterPage" is clicked
    Then the current url contains the following keyword: "=account/success&l"

  Scenario: User remains on Register Page when continue button is not clicked during the register flow
    When the registration form is completed with valid random data
    And "privacyToggle" from "RegisterPage" is clicked
    Then the current url contains the following keyword: "route=account/register&language=en-gb"

  Scenario: User remains on Register Page when privacy conditions are not accepted during the registration flow
    When the registration form is completed with valid random data
    And "continueButton" from "RegisterPage" is clicked
    Then the current url contains the following keyword: "route=account/register&language=en-gb"

  Scenario Outline: Error message is displayed when trying to register with invalid <attribute> data
    When register form is populated with the following details:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | <email>     |
      | password  | <password>  |
    And "continueButton" from "RegisterPage" is clicked
    Then the following list of error messages is displayed:
      | Warning: You must agree to the Privacy Policy!   |
      | <attribute> must be between 1 and 32 characters! |
    Examples:
      | firstName                         | lastName                          | email  | password | attribute  |
      | Skawesdfrtgqwertyujkgortpdkslwefb | random                            | random | random   | First Name |
      | Valid                             | 415236451278956231478523456963698 | random | random   | Last Name  |

  Scenario Outline: Error message is displayed when trying to register with generic invalid <attribute> data
    When the following fields from "RegisterPage" are populated with data:
      | firstNameInput | <firstName> |
      | lastNameInput  | <lastName>  |
      | emailInput     | <email>     |
      | passwordInput  | <password>  |
    And "continueButton" from "RegisterPage" is clicked
    Then the following list of error messages is displayed:
      | Warning: You must agree to the Privacy Policy!   |
      | <attribute> must be between 1 and 32 characters! |
    Examples:
      | firstName                         | lastName                          | email          | password | attribute  |
      | Skawesdfrtgqwertyujkgortpdkslwefb | Valid                             | test@gmail.com | random   | First Name |
      | Valid                             | 415236451278956231478523456963698 | test@gmail.com | random   | Last Name  |
