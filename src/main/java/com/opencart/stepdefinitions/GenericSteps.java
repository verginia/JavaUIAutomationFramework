package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

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

    @When("{string} from {string} is clicked")
    public void fromIsClicked(String elementName, String elementContainingPage) {
        try {
            Class classInstance = Class.forName("com.opencart.pageobjects." + elementContainingPage);
            Field webElementField = classInstance.getDeclaredField(elementName);
            webElementField.setAccessible(true);
            WebElement webElementToBeClicked = (WebElement)
                    webElementField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
            ScrollManager.scrollToElement(webElementToBeClicked);
            webElementToBeClicked.click();
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("the following fields from {string} are populated with data:")
    public void theFollowingFieldsFromArePopulatedWithData(String pageName, Map<String, String> fieldValuesMap)
            throws ClassNotFoundException {
        Class classInstance = Class.forName("com.opencart.pageobjects." + pageName);

        fieldValuesMap.forEach((fieldName, valueToBeEntered)->{
            try {
                Field webElementField = classInstance.getDeclaredField(fieldName);
                webElementField.setAccessible(true);
                WebElement webElementForDataInsertion = (WebElement)
                        webElementField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
                ScrollManager.scrollToElement(webElementForDataInsertion);
                webElementForDataInsertion.sendKeys(valueToBeEntered);
            } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }

        });
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
