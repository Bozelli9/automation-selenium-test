package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private By userNameInputLocator = By.id("username");
    private By passwordInputLocator = By.id("password");
    private By submitButtonLocator = By.id("submit");
    private By errorMessageLocator = By.id("error");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visitUrl("https://practicetestautomation.com/practice-test-login/");
    }

    public void enterUsername(String username){
        driver.findElement(userNameInputLocator).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordInputLocator).sendKeys(password);
    }

    public void clickSubmit(){
        driver.findElement(submitButtonLocator).click();
    }

    public SuccessLoginPage executeLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        return new SuccessLoginPage(driver);
    }

    public String getErrorMessage(){
      WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
      return errorMessageElement.getText();
    }
}
