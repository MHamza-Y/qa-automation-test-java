package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleHomePage {

    WebDriver driver;
    @FindBy(css = ".gNO89b")
    WebElement searchButton;

    @FindBy(css = ".gLFyf.gsfi")
    WebElement searchField;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void goTo() {
        driver.get("http://google.com");
    }
    public void EnterIntoSearchField(String searchString) {
        searchField.click();
        searchField.sendKeys(searchString);
    }
    public GoogleResultPage clickSearch() {
        searchButton.click();
        return new GoogleResultPage(driver);
    }

    public GoogleResultPage search(String searchString) {
        this.EnterIntoSearchField(searchString);
        return this.clickSearch();
    }
}
