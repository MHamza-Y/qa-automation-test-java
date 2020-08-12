package pages.emumba;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmumbaHomePage {
    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy (xpath = "//span[text()=\"Login\"]/..")
    WebElement loginButton;
    @FindBy (css = "[href=\"/submit\"]")
    WebElement postFlyerButton;
    @FindBy (xpath = "//span[text()=\"Logout\"]/..")
    WebElement logoutButton;
    @FindBy (css = ".MuiTypography-h5")
    WebElement latestFlyerHead;
    @FindBy (css = ".MuiTypography-body2.MuiTypography-paragraph")
    WebElement latestFlyerBody;


    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public EmumbaHomePage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }


    public EmumbaLoginPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return new EmumbaLoginPage(driver,waitTime);
    }

    public EmumbaPostFlyerPage clickPostFlyerButton() {
        wait.until(ExpectedConditions.visibilityOf(postFlyerButton));
        postFlyerButton.click();
        return new EmumbaPostFlyerPage(driver,waitTime);
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }

    public String getLatestPostHeading() {
        wait.until(ExpectedConditions.visibilityOf(latestFlyerHead));
        return latestFlyerHead.getAttribute("innerText");
    }
    public  String getLatestPostBody() {
        wait.until(ExpectedConditions.visibilityOf(latestFlyerBody));
        return latestFlyerBody.getAttribute("innerText");
    }
    public String getUrl() {
        return "https://emumba-test.herokuapp.com/";
    }
}
