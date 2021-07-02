package com.td.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Go Back\"]")
    WebElement backButton;
    @FindBy(xpath = "//android.widget.EditText[@text='User name']")
    WebElement userName ;
    @FindBy(xpath = "//android.widget.EditText[@text='Password']")
    WebElement password ;

    public void clickOnBackButton(){
        backButton.click();
    }
    public void enterTextOnUserField(String usrName){
        userName.sendKeys(usrName);
    }
    public void enterTextOnPasswordField(String pass){
        password.sendKeys(pass);
    }






}
