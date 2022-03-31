package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    public static final String USER_DIR = System.getProperty("user.dir");
    protected final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        final ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        driver.set(chromeDriver);
    }

    protected void  openIceHrmAppInWeb() {
        final WebDriver webDriver = getWebDriver();
        webDriver.get(ConfigService.getInstance().getUrl());
    }

    protected WebDriver getWebDriver() {
        return driver.get();
    }

    @AfterMethod
    public void tearDown (ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(testResult);
        }
        getWebDriver().quit();
    }

    private void takeScreenshot(final ITestResult testResult) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getWebDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        final Date currentDate = new Date();
        final String timeStamp = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss").format(currentDate);
        final String testName = testResult.getName() + "-" +  timeStamp;
        String fileWithPath = MessageFormat.format("{0}/target/test-results/{1}.png",USER_DIR, testName);
        File destFile = new File(fileWithPath);
        FileUtils.copyFile(srcFile,destFile);
    }
}
