package com.practicetestautomation.tests.login.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests {
    private WebDriver driver;
    private Logger logger;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(String browser) {
        logger = Logger.getLogger(LoginTests.class.getName());
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

        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFunctionality() {
        logger.info("Starting testLoginFuncionality");

        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Type username");
        usernameInput.sendKeys("student");


        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type password");
        passwordInput.sendKeys("Password123");


        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Click submit button");
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        logger.info("Verify the login funcionality");
        String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String expectedMessage = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(expectedMessage));


        WebElement logOutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negativeLoginTest");
        WebElement usernameInput = driver.findElement(By.id("username"));
        logger.info("Type username");
        usernameInput.sendKeys(username);


        WebElement passwordInput = driver.findElement(By.id("password"));
        logger.info("Type password");
        passwordInput.sendKeys(password);


        WebElement submitButton = driver.findElement(By.id("submit"));
        logger.info("Click Submit button");
        submitButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        WebElement errorMessage = driver.findElement(By.id("error"));
        Assert.assertTrue(errorMessage.isDisplayed());

        String actualErrorMessage = errorMessage.getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
}