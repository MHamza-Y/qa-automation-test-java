package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleHomePage {

    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy(css = ".gNO89b")
    WebElement searchButton;

    @FindBy(css = ".gLFyf.gsfi")
    WebElement searchField;

    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public GoogleHomePage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * Go to this page on browser
     */
    public void goTo() {
        driver.get("http://google.com");
    }

    /**
     * Enter string into search field
     * @param searchString the string to search
     */
    public void EnterIntoSearchField(String searchString) {
        searchField.click();
        searchField.sendKeys(searchString);
    }

    /**
     * click search button to start searching for given string
     * @return returns google results page object
     */
    public GoogleResultPage clickSearch() {
        searchButton.click();
        return new GoogleResultPage(driver,waitTime);
    }

    /**
     * Performs search on google
     * @param searchString keyword to search for
     * @return returns google results page object
     */
    public GoogleResultPage search(String searchString) {
        this.EnterIntoSearchField(searchString);
        return this.clickSearch();
    }

    public boolean checkPageLoaded() {
        return searchButton.getAttribute("value").equals("Google Search");
    }
}
