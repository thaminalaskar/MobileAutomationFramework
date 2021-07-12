package com.tdbank.datatest;

import org.testng.annotations.DataProvider;

public class DataProviderTest {
    //For login page data
    @DataProvider(name ="logInPageCredential")
    public static Object [][] getRegistrationInvalidateData(){
        return new Object[][]{
                {"Tommy420","15748"}
        };
    }
}
