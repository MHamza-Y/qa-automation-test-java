package pages.emumba;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class EmumbaLoginPage {
    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy(css = "[href=\"/register\"]")
    WebElement notAUserYet;
    @FindBy(css = "[name=email]")
    WebElement emailField;
    @FindBy(css = "[name=password]")
    WebElement passwordField;
    @FindBy(css = "[type=submit]")
    WebElement loginButton;

    /**
     * constructor
     *
     * @param driver   webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public EmumbaLoginPage(WebDriver driver, int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver, waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * clicks on not a user yet link
     * @return returns registration page object
     */
    public EmumbaRegisterPage clickNotAUserYet() {
        wait.until(ExpectedConditions.visibilityOf(notAUserYet));
        notAUserYet.click();
        return new EmumbaRegisterPage(driver, waitTime);
    }

    /**
     * logs in to emumba site
     * @param email email required for login
     * @param password password required for login
     * @return returns home page object
     */
    public EmumbaHomePage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(notAUserYet));
        emailField.click();
        emailField.sendKeys(email);
        passwordField.click();
        passwordField.sendKeys(password);
        loginButton.click();
        return new EmumbaHomePage(driver, waitTime);
    }
}
