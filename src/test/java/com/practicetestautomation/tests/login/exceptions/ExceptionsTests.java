package com.practicetestautomation.tests.login.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.beans.Visibility;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionsTests {
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

        driver.get("https://practicetestautomation.com/practice-test-exceptions/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void noSuchElement() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btnAdd = driver.findElement(By.id("add_btn"));
        btnAdd.click();

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        Assert.assertTrue(row.isDisplayed());

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

    @Test
    public void timeoutException() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement btnAdd = driver.findElement(By.id("add_btn"));
        btnAdd.click();


        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));

        Assert.assertTrue(row.isDisplayed());


    }

    @Test
    public void elementNotInteractableException() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement btnAdd = driver.findElement(By.id("add_btn"));
        btnAdd.click();

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input")));
        row.sendKeys("text");

        WebElement btnSave = driver.findElement(By.xpath("//div[@id='row2']/button[@name='Save']"));
        btnSave.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmation")));
        String actualMsg = successMessage.getText();
        String expectedMessage = "Row 2 was saved";

        Assert.assertEquals(actualMsg, expectedMessage, "Message is not expected");
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