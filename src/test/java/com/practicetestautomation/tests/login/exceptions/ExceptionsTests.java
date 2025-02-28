package com.practicetestautomation.tests.login.exceptions;

import com.practicetestautomation.pageobjects.ExceptionsPage;
import com.practicetestautomation.tests.login.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;


public class ExceptionsTests extends BaseTest {

    @Test
    public void noSuchElement() {
        
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");
    }

    @Test
    public void timeoutException() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        Assert.assertTrue(exceptionsPage.isRowTwoDisplayedAfterWait(),"Row 2 is not displayed");


    }

    @Test
    public void elementNotInteractableException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking button");
        exceptionsPage.clickButton();
        exceptionsPage.isRowTwoDisplayedAfterWait();
        exceptionsPage.foodKeys("Sushi");
        exceptionsPage.clickSaveButton();
        Assert.assertEquals(exceptionsPage.getSucessMessage(), "Row 2 was saved", "Message is not expected");
    }


    @Test
    public void invalidElementException() {
        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking edit button");
        exceptionsPage.clickEditButton();
        exceptionsPage.clearName();
        exceptionsPage.foodKeysRow1("Sushi");
        exceptionsPage.clickSaveButtonRow1();

        Assert.assertEquals(exceptionsPage.getSucessMessage(), "Row 1 was saved", "Row 1 is not saved");

    }

    @Test
    public void staleElementException() {

        ExceptionsPage exceptionsPage = new ExceptionsPage(driver);
        logger.info("Opening Browser");
        exceptionsPage.visit();
        logger.info("Clicking add button");
        exceptionsPage.clickButton();

        Assert.assertTrue(exceptionsPage.instructionIsDisplayed(),"The message still displayed");


    }
}