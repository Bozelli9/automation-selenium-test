package com.practicetestautomation.tests.login.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ExceptionsTests.class);
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger = Logger.getLogger(ExceptionsTests.class.getName());
        logger.setLevel(Level.INFO);
        logger.info("Running test in " + browser);
        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void noSuchElement() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");
    }

    @Test
    public void timeoutException() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");


    }

    @Test
    public void elementNotInteractableException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        exceptionsPage.isRowTwoDisplayedAfterWait();
        exceptionsPage.foodKeys("Sushi");
        exceptionsPage.clickSaveButton();
        Assert.assertEquals(exceptionsPage.getSucessMessage(), "Row 2 was saved", "Message is not expected");
    }


    @Test
    public void invalidElementException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking edit button");
        exceptionsPage.clickEditButton();
        exceptionsPage.clearName();
        exceptionsPage.foodKeysRow1("Sushi");
        exceptionsPage.clickSaveButton();

        Assert.assertEquals(exceptionsPage.getSucessMessage(), "Row 1 was saved", "Row 1 is not saved");

    }

    @Test
    public void staleElementException() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking add button");
        exceptionsPage.clickButton();

        Assert.assertTrue(exceptionsPage.instructionIsDisplayed(),"The message still displayed");


    }
}