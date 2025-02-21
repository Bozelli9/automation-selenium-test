package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SuccessLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By logOutButton = By.linkText("Log Out");


    public SuccessLoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean sucessMessage(){
        try {
            return driver.findElement(logOutButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
