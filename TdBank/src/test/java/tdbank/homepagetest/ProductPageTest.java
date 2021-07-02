package tdbank.homepagetest;

import com.td.pages.ProductPage;
import common.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporting.ExtentTestManager;

public class ProductPageTest extends TestBase {
    private static final Logger logger = Logger.getLogger(ProductPageTest.class);


    @Test
    public void validateProductPageTest() throws InterruptedException {
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
        productPage.clickOnProductButton();
        Assert.assertTrue(productPage.productButtonOnDisplay());
        Assert.assertTrue(productPage.checkingButtonIsDisplay());

        Assert.assertTrue(productPage.textOnCheckingAccount());
        ExtentTestManager.log("User in checking account", logger);
    }


}
