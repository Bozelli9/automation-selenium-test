package com.practicetestautomation.tests.login.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btnEdit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit_btn")));
        btnEdit.click();

        WebElement rowInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row1']/input")));
        rowInputField.clear();
        rowInputField.sendKeys("Sushi");

        WebElement btnSave = driver.findElement(By.xpath("//div[@id='row1']/button[@name='Save']"));
        btnSave.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMsg = successMessage.getText();
        String expectedMessage = "Row 1 was saved";

        Assert.assertEquals(actualMsg, expectedMessage, "Message is not expected");


    }

    @Test
    public void staleElementException() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement addButton = driver.findElement(By.id("add_btn"));
        addButton.click();

        Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("instructions"))),"The message still displayed");


    }
}