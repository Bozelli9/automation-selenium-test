package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExceptionsPage extends BasePage {
    private By addButtonLocator = By.id("add_btn");
    private By row2Locator = By.xpath("//div[@id='row2']/input");

    public ExceptionsPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visitUrl("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void clickButton(){
        driver.findElement(addButtonLocator).click();
    }

    public boolean waitForDisplayed() {
        return waitIsDisplayed(row2Locator);
    }

}
