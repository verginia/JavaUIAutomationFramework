package com.opencart.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static String webDriverType = ConfigReaderManager.getProperty("browserType");
    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
             //   WebDriverManager.chromedriver().setup();
             ChromeOptions options = new ChromeOptions();
             if(ConfigReaderManager.getProperty("isIncognitoChromeEnabled").equals("true")){
                 options.addArguments("--incognito");
             }
//               options.addArguments("--remote-allow-origins=*")
//               options.addArguments("--headless")
               driver = new ChromeDriver(options);
                System.out.println("The Chrome Driver was initiated!");
                break;
            case "FIREFOX":
            //    WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                System.out.println("The FireFox Driver was initiated!");
                break;
            case "EDGE":
           //     WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                System.out.println("The Edge Driver was initiated!");
                break;
            case "SAFARI":
            //    WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                System.out.println("The Safari Driver was initiated!");
                break;
            default:
                System.out.println("There is not defined such a driver:" + webDriverType);
        }
    }

    // Metoda statica pentru a obtine instanta Singleton
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    // Metoda publica pentru a obtine driverul web
    public WebDriver getDriver() {
        if (driver == null) {
            // getInstance();
            DriverManager.getInstance();
        }
        return driver;
    }
    // driver-ul si instanta se seteaza cu null pentru a pregati browser-ul de noua sesiune
    public void quitTheDriver(){
        driver.quit();
        driver = null;
        instance = null;
        System.out.println("The Browser is closed and session is set to null");
    }
}
