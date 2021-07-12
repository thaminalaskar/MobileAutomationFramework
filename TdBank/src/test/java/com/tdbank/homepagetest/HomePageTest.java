package com.tdbank.homepagetest;

import com.td.pages.HomePage;
import com.td.pages.LoginPage;
import com.tdbank.datatest.DataProviderTest;
import common.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;

public class HomePageTest extends TestBase {

private static final Logger logger = Logger.getLogger(HomePageTest.class);

    HomePage homePage;
    LoginPage loginPage;
    @Test(enabled = false)
    public void validateUserCanClickONAccountsButtonTest(){
        homePage = PageFactory.initElements(driver,HomePage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        Assert.assertTrue(homePage.accountsDisplay());
        homePage.clickOnAccounts();
        loginPage.clickOnBackButton();
        ExtentTestManager.log("Accounts click from home page",logger);
    }
    @Test(enabled = false)
    public void validateUserCanClickOnTransferButtonTest() {
        homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.clickOnTransferBtn();
        Assert.assertTrue(homePage.transferButtonDisplay());
        ExtentTestManager.log("click on transfer button",logger);
    }
    @Test(dataProviderClass = DataProviderTest.class,dataProvider = "logInPageCredential")
    public void validateUserEnterCredentialTest(String usr , String pass){
        homePage = PageFactory.initElements(driver,HomePage.class);
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        homePage.clickOnAccounts();
        loginPage.enterTextOnUserField(usr);
        loginPage.enterTextOnPasswordField(pass);
    }











}
