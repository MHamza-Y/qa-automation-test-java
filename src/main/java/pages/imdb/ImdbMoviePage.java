package pages.imdb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.Console;

public class ImdbMoviePage {
    WebDriver driver;
    int waitTime;
    WebDriverWait wait;
    @FindBy(css = "#titleCast .see-more>a")
    WebElement seeFullCast;

    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public ImdbMoviePage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks on see full cast button
     * @return returns a See full cast movie page object
     */
    public ImdbMovieCastPage clickSeeFullCast() {
        seeFullCast.click();
        return new ImdbMovieCastPage(driver,waitTime);
    }
}
