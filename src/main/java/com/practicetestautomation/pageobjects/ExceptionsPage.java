package com.practicetestautomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExceptionsPage extends BasePage {
    private By addButtonLocator = By.id("add_btn");
    private By row1Locator = By.xpath("//div[@id='row1']/input");
    private By row2Locator = By.xpath("//div[@id='row2']/input");
    private By saveButtonLocatorRow1 = By.xpath("//div[@id='row1']/button[@name='Save']");
    private By saveButtonLocator = By.xpath("//div[@id='row2']/button[@name='Save']");
    private By msgConfirmation = By.id("confirmation");
    private By editButtonLocator = By.id("edit_btn");
    private By instructionLocator = By.id("instructions");

    public ExceptionsPage(WebDriver driver){
        super(driver);
    }

    public boolean instructionIsDisplayed(){
        return isNotDisplayed(instructionLocator);
    }

    public void clickEditButton(){
        driver.findElement(editButtonLocator).click();
    }

    public void foodKeysRow1(String name){
        driver.findElement(row1Locator).sendKeys(name);
    }

    public void clearName(){
        driver.findElement(row1Locator).clear();
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

    public void clickSaveButtonRow1(){
        driver.findElement(saveButtonLocatorRow1).click();
    }

}
