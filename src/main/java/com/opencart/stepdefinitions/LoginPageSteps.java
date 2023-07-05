package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LoginPageSteps {

    private WebDriver driver = DriverManager.getInstance().getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    @And("the following set of credentials is entered into the login form:")
    public void theFollowingSetOfCredentialsIsEnteredIntoTheLoginForm(List<String> credentialsList) {
        loginPage.fillInTheLoginFields(credentialsList.get(0), credentialsList.get(1));
    }

//    @When("login button is clicked")
//    public void loginButtonIsClicked() {
//        loginPage.clickOnTheLoginButton();
//    }
}
