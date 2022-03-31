package pages;

import core.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends PageObject {
    By myProfileLink = By.id("myProfileLink");
    By peopleLink = By.id("employeeLink");
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public ProfilePage clickViewProfile() {
        click(myProfileLink);
        return new ProfilePage(webDriver);
    }

}
