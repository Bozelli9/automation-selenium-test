package com.practicetestautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {

    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void beforeSetUp(){
        driver = new ChromeDriver();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }


    @AfterMethod(alwaysRun = true)
    public void setUp() {
        driver.quit();

    }

    @Test(groups = {"positive", "regression", "smoke"})
    public void testLoginFuncionality() {

        WebElement usernameText = driver.findElement(By.id("username"));
        usernameText.sendKeys("student");

        WebElement passwordText = driver.findElement(By.id("password"));
        passwordText.sendKeys("Password123");

        WebElement submitBtn = driver.findElement(By.id("submit"));
        submitBtn.click();

        String anotherUrl = "https://practicetestautomation.com/logged-in-successfully/";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, anotherUrl);

        String loggedSucess = "Congratulations student. You successfully logged in!";
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(loggedSucess));


        WebElement buttonSubmit = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(buttonSubmit.isDisplayed());


    }
}
