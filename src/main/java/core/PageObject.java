package core;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.text.MessageFormat;
import java.time.Duration;

public class PageObject {
    private final WebDriverWait wait;
    protected final WebDriver webDriver;
    protected PageObject(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigService.getInstance().getTimeout()));
    }
    protected void enter(String text, By locator) {
        waitAndGetElement(locator).sendKeys(text);
        final String message = MessageFormat.format("Entered {0} into {1}", locator);
        log(message);
    }
    protected void click(By locator) {
        waitAndGetElement(locator).click();
        final String message = MessageFormat.format("Clicked {0}", locator);
        log(message);
    }
    protected String getText(By locator) {
        final String text = waitAndGetElement(locator).getText();
        final String message = MessageFormat.format("Retrieved {0} by locating {1}", text, locator);
        log(message);
        return text;
    }
    private void log(String message) {
        Reporter.log(message, 1, true);
    }
    private WebElement waitAndGetElement(final By locator) {
        return wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
