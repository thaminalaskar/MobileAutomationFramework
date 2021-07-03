package connections;

import com.relevantcodes.extentreports.ExtentReports;
import common.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.log4testng.Logger;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConnection {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"R38JB02DEBN");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9");
        cap.setCapability(MobileCapabilityType.APP_PACKAGE,"com.tdbank");
        cap.setCapability(MobileCapabilityType.APP_ACTIVITY,"com.td.dcts.android.us.app.SplashScreenActivity");

        AppiumDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"),cap);
        System.out.println(driver.getPageSource());

        Thread.sleep(2000);
        driver.findElement(By.xpath("//android.widget.Button[@text='ACCOUNTS']")).click();

    }


    private static final Logger LOGGER = Logger.getLogger(TestBase.class);
    public static AppiumDriver driver;
    private static ExtentReports extent;




    /**
     * This method will swipe either up, Down, left or Right according to the
     * direction specified. This method takes the size of the screen and uses
     * the swipe function present in the Appium driver to swipe on the screen
     * with a particular timeout. There is one more method to implement swipe
     * using touch actions, which is not put up here.
     *
     * @param direction The direction we need to swipe in.
     * @param swipeTime The swipe time, ie the time for which the driver is supposed
     *                  to swipe.
     * @param offset    The offset for the driver, eg. If you want to swipe 'up', then
     *                  the offset is the number of pixels you want to leave from the
     *                  bottom of the screen t start the swipe.
     * @Author - Zann
     * @Modified By -
     */


    public static void functionSwipe(String direction, int swipeTime, int offset) {
        Dimension size;
        size = (driver).manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        if (direction.equalsIgnoreCase("Up")) {
            ((AppiumDriver<WebElement>) (driver)).swipe(startx / 2, starty - offset, startx / 2, endy, swipeTime);
        } else if (direction.equalsIgnoreCase("Down")) {
            ((AppiumDriver<WebElement>) (driver)).swipe(startx / 2, endy + offset, startx / 2, starty, swipeTime);
        } else if (direction.equalsIgnoreCase("Right")) {
            starty = size.height / 2;
            endy = size.height / 2;
            startx = (int) (size.width * 0.10);
            int endx = (int) (size.width * 0.90);
            ((AppiumDriver<WebElement>) (driver)).swipe(startx + offset, starty, endx, endy, swipeTime);
        } else if (direction.equalsIgnoreCase("Left")) {
            starty = size.height / 2;
            endy = size.height / 2;
            startx = (int) (size.width * 0.90);
            int endx = (int) (size.width * 0.10);
            ((AppiumDriver<WebElement>) (driver)).swipe(startx - offset, starty, endx, endy, swipeTime);
        }


















    }












}
