package com.opencart;


import com.opencart.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

public class TestRunner {
    public static void main(String[] args) {
        //        DriverManager driverManager = DriverManager.getInstance();
        //        WebDriver driver1 = driverManager.getDriver();

        // Define a driver object that will be used for future actions.
        WebDriver driver = DriverManager.getInstance().getDriver();

        driver.get("https://mail.google.com/mail/u/0/?tab=rm&ogbl#inbox");

        String currentWindowName = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.youtube.com/");
        driver.close();

        driver.switchTo().window(currentWindowName);
        driver.get("https://protv.md/");
        driver.quit();

        System.out.println("The driver is closed");
    }
}




/*import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestRunner {
    public static void main(String[] args) throws InterruptedException {
      //  WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com/");
        Thread.sleep(3000);

        driver.quit();
    }
}
*/
