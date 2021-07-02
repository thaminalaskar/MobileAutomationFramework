package com.td.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @FindBy(xpath = "//android.widget.Button[@text='ACCOUNTS']")
    private WebElement accounts ;
    @FindBy(xpath="//android.widget.Button[@text='TRANSFER']")
    private WebElement transferBtn;

   public void clickOnAccounts(){
       accounts.click();
   }
   public boolean accountsDisplay(){
      return accounts.isDisplayed();
   }
    public void clickOnTransferBtn(){
        transferBtn.click();
    }
    public boolean transferButtonDisplay(){
        return transferBtn.isDisplayed();
    }

}
