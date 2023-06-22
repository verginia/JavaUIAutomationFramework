package com.opencart;

import com.opencart.managers.DataFakerManager;
import com.opencart.managers.DriverManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

public class RegistrationFlowTestSuite {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    static int counter = 0;

    @BeforeAll
    public static void beforeAllExecution() {
        System.out.println("The execution for Registration flow starts: \n");
    }

    @BeforeEach
    public void executeTheCodeBeforeEachTestFromThisClass() {
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        counter++;
        System.out.println("The TestCase number " + counter + " started!");
    }

    @Test
    @DisplayName("The url contains success keyword after registration with valid data")
    public void registerFlowRedirectsTheUserToTheCorrectUrl() throws InterruptedException {
        System.out.println(homePage);
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21, 22);

        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language=en-gb&customer");
        String errorMessage = "The url " + driver.getCurrentUrl() + " contains success keyword";
        Assertions.assertTrue(urlContainsTheCorrectKeyWords, errorMessage);
    }

    @Test
    @DisplayName("The url contains register keyword when privacy policy is not accepted")
    public void registerFlowIsBlockedByPrivacyPolicyToggleThatIsNotAccepted() throws InterruptedException {
        homePage.navigateToRegisterPageFromHeaderMenu();

        String firstName = DataFakerManager.getRandomName();
        String lastName = DataFakerManager.getRandomName();
        String email = DataFakerManager.getRandomEmail();
        String password = DataFakerManager.getRandomPassword(21, 22);

        registerPage.fillInTheRegisterForm(firstName, lastName, email, password);
        // Do not enable privacy policy toggle
        //registerPage.switchOnThePrivacyToggle(driver);
        registerPage.clickOnContinueButton();
        Thread.sleep(1000);
        System.out.println(driver.getCurrentUrl());

        boolean urlContainsTheCorrectKeyWords = driver.getCurrentUrl().contains("/index.php?route=account/success&language=en-gb&customer");
        String errorMessage = "The url " + driver.getCurrentUrl() + " does not contain success keyword";
        Assertions.assertFalse(urlContainsTheCorrectKeyWords, errorMessage);

        boolean urlContainsRegisterKeyword = driver.getCurrentUrl().contains("/index.php?route=account/register&language=en-gb");
        Assertions.assertTrue(urlContainsRegisterKeyword, "The url belongs to register page");
    }

    @AfterEach
    public void executeThisMethodAfterEachTestCase() {
        DriverManager.getInstance().quitTheDriver();
        System.out.println("The TestCase number " + counter + " finished");
    }

    @AfterAll
    public static void afterAllExecution() {
        System.out.println("\n The execution was finished");
    }
}
