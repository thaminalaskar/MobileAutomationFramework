package com.td.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage  {

    @FindBy(xpath = "//android.widget.TextView[@text='Products']")
    WebElement productButton;

    @FindBy(xpath = "//android.widget.TextView[@text='Checking Accounts']")
    WebElement checkingAccount ;

    @FindBy(xpath = "//android.widget.TextView[@text=All TD Bank personal checking accounts include:]")
    WebElement textCheckingAccount;

    public boolean productButtonOnDisplay(){
        return productButton.isDisplayed();
    }

    public boolean checkingButtonIsDisplay(){
        return  checkingAccount.isDisplayed();
    }

    public void clickOnProductButton() throws InterruptedException {
        sleep(10);
        functionSwipe("Up",200,200);
        productButton.click();
        checkingAccount.click();
        sleep(10);
        functionSwipe("Up",200,200);
    }

    private void functionSwipe(String up, int i, int i1) {
    }

    private void sleep(int i) {
    }

    public boolean textOnCheckingAccount(){
        return textCheckingAccount.isDisplayed();
    }


}
