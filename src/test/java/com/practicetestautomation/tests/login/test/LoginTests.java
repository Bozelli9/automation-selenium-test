package com.practicetestautomation.tests.login.test;

import com.practicetestautomation.pageobjects.LoginPage;
import com.practicetestautomation.pageobjects.SuccessLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTests extends BaseTest{

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
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

    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFunctionality() {
        logger.info("Starting testLoginFuncionality");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        SuccessLoginPage successLoginPage = loginPage.executeLogin("student","Password123");
        successLoginPage.load();
        logger.info("Verify the login funcionality");
        Assert.assertTrue(successLoginPage.getPageSource().contains("Congratulations student. You successfully logged in!"));
        Assert.assertEquals(successLoginPage.actualUrl(), "https://practicetestautomation.com/logged-in-successfully/" );
        Assert.assertTrue(successLoginPage.logOutButtonDisplayed());

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups = {"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        logger.info("Starting negativeLoginTest");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.visit();
        loginPage.executeLogin(username, password);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);


    }
}