package pages;

import core.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageObject {
    By usernameTextBox = By.id("username");
    By passwordTextBox = By.id("password");
    By loginButton = By.xpath("//button[contains(text(),'Log in ')]");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void enterUsername(String username) {
        enter(username, usernameTextBox);
    }
    public void enterPassword(String password) {
        enter(password, passwordTextBox);
    }
    public DashboardPage clickLogin() {
        click(loginButton);
        return new DashboardPage(webDriver);
    }

}
