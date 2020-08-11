package pages.emumba;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmumbaRegisterPage {
    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy (css = "[name=\"first_name\"]")
    WebElement firstNameField;
    @FindBy (css = "[name=\"last_name\"]")
    WebElement lastNameField;
    @FindBy (css = "[name=\"email\"]")
    WebElement emailField;
    @FindBy (css = "[name=\"password\"]")
    WebElement passwordField;
    @FindBy (css = "[name=\"confirm_password\"]")
    WebElement confirmPassField;
    @FindBy (css = "[type=\"submit\"]")
    WebElement submitButton;


    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public EmumbaRegisterPage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    public EmumbaLoginPage register(String firstname,String lastname,String email,String password, String confirmPassword) {
        firstNameField.click();
        firstNameField.sendKeys(firstname);
        lastNameField.click();
        lastNameField.sendKeys(lastname);
        emailField.click();
        emailField.sendKeys(email);
        passwordField.click();
        passwordField.sendKeys(password);
        confirmPassField.click();
        confirmPassField.sendKeys(confirmPassword);
        submitButton.click();
        return new EmumbaLoginPage(driver,waitTime);
    }
}
