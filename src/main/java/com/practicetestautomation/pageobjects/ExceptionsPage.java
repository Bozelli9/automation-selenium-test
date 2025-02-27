package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {
    private By addButtonLocator = By.id("add_btn");
    private By row2Locator = By.xpath("//div[@id='row2']/input");
    private By saveButtonLocator = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By msgConfirmation = By.id("confirmation");

    public ExceptionsPage(WebDriver driver){
        super(driver);
    }

    public void visit(){
        super.visitUrl("https://practicetestautomation.com/practice-test-exceptions/");
    }

    public void clickButton(){
        driver.findElement(addButtonLocator).click();
    }

    public boolean isRowTwoDisplayedAfterWait() {
        return waitIsDisplayed(row2Locator);
    }

    public String getSucessMessage() {
        return waitForElement(msgConfirmation).getText();
    }

    public void foodKeys(String name){
        driver.findElement(row2Locator).sendKeys(name);
    }

    public void clickSaveButton(){
        driver.findElement(saveButtonLocator).click();
    }

}
