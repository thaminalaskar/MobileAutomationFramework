package common;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporting.ExtentManager;
import reporting.ExtentTestManager;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static final Logger LOGGER = Logger.getLogger(TestBase.class);
    public static AppiumDriver driver;
    public static AppiumDriver appiumDriver = null;
    public static String platform = null;
    private static ExtentReports extent;

    /**
     * @param platform        : platform of the mobile device
     * @param deviceName      : udid if the device
     * @param platformVersion
     * @throws MalformedURLException
     */

    //screenshot
    private static void captureScreenshot(WebDriver driver, String screenshotName) {
        DateFormat dateFormat = new SimpleDateFormat("HH_mm_ss");
        Date date = new Date();
        // --> dateFormat.format(date);
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + dateFormat.format(date) + ".jpg"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }

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

    public static void swipeFromOneToAnother(MobileElement element1, MobileElement element2) {
        try {
            TouchActions actions = new TouchActions(appiumDriver);
            appiumDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Point location1 = element1.getLocation();
            int X1 = location1.getX();
            int Y1 = location1.getY();
            Point location2 = element2.getLocation();
            int X2 = location2.getX();
            int Y2 = location2.getY();

            actions.scroll(X1, Y1).move(X2, Y2).release().perform();
        } catch (Exception e) {

        }
    }

    /**
     * @param platform        : platform of the mobile device
     * @param deviceName      : udid if the device
     * @param platformVersion
     * @throws MalformedURLException
     */
    @Parameters({"platform", "deviceName", "platformVersion"})
    @BeforeMethod
    public void getAppiumDriver(String platform, String deviceName, String platformVersion) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        if (platform.equalsIgnoreCase("android")) {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.tdbank");
            cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.td.dcts.android.us.app.SplashScreenActivity");
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);
        } else {
            //ios device code
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //reporting starts
    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    private String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }

        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    //reporting finish

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    @AfterMethod
    public void cleanUp() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
        LOGGER.info("driver closed");
    }


    public void clickByXpath(String locator) {
        appiumDriver.findElement(By.xpath(locator)).click();
    }

    public void clickByXpath(MobileElement locator) {
        locator.click();
    }

    public void clickByXpathWebElement(WebElement locator) {
        locator.click();
    }

    public void sleep(int sec) throws InterruptedException {
        Thread.sleep(1000 * sec);
    }

    public void typeByXpath(String locator, String value) {
        appiumDriver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public List<String> getTexts(List<WebElement> elements) {
        List<String> text = new ArrayList<String>();

        for (WebElement element : elements) {
            text.add(element.getText());
        }

        return text;
    }

    public void scrollToElement(AppiumDriver driver, String element) {
        MobileElement we = (MobileElement) driver.findElementByXPath(element);
        driver.scrollTo(we.getText());
    }

    public void alertAccept(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.err.println("No alert visible in 5 seconds");
        }
    }

    public void scrollAndClickByName(String locator) {
        appiumDriver.scrollTo(locator).click();
    }


}
