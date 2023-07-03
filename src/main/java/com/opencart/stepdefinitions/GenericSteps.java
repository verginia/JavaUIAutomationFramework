package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GenericSteps {

    private WebDriver driver = DriverManager.getInstance().getDriver();

    @Given("The {string} link is accessed")
    public void theLinkIsAccessed(String webAddress) {
        driver.get(webAddress);
        System.out.println("The " + webAddress + " link is accessed by driver");
    }

    @Then("the following list of error messages is displayed:")
    public void theFollowingListOfErrorMessagesIsDisplayed(List<String> errorMessagesList) throws InterruptedException {
        Thread.sleep(500);
        for (int i = 0; i < errorMessagesList.size(); i++) {
            String elementXpath = ".//*[contains(text(),'" + errorMessagesList.get(i) + "')]";
            WebElement errorMessageElement = driver.findElement(By.xpath(elementXpath));
            boolean isErrorMessageNumberIDisplayed = errorMessageElement.isDisplayed();
            Assertions.assertTrue(isErrorMessageNumberIDisplayed, "The error message " + errorMessagesList.get(i) +
                    "is displayed");
        }
    }

    @Then("the current url contains the following keyword: {string}")
    public void theCurrentUrlContainsTheFollowingKeyword(String keyword) throws InterruptedException {
        Thread.sleep(500);
        boolean urlContainsCollectedString = driver.getCurrentUrl().contains(keyword);
        System.out.println(driver.getCurrentUrl());
        Assertions.assertTrue(urlContainsCollectedString, "The " + keyword +
                "is present within the URL");
    }

//    public void theFollowingListOfErrorMessagesIsDisplayed(List<String> errorMessagesList) throws InterruptedException {
//        Thread.sleep(500);
//        errorMessagesList.forEach(errorMessage ->{
//            boolean messageIsDisplayed = driver.findElement(By.xpath(".//*[contains(text(),'" + errorMessage
//                    + "')]")).isDisplayed();
//            Assertions.assertTrue(messageIsDisplayed, "The [" + errorMessage + "] is displayed");
//        });
//    }
}
