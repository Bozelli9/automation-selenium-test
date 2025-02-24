package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessLoginPage extends BasePage{
    private By logOutButton = By.linkText("Log out");


    public SuccessLoginPage(WebDriver driver){
        super(driver);
    }

    public boolean logOutButtonDisplayed(){
        return isDisplayed(logOutButton);
    }

    public void load() {
        waitForElement(logOutButton);
    }

}
