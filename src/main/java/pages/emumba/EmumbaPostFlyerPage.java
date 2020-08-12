package pages.emumba;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public class EmumbaPostFlyerPage {
    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy (css = ".MuiBox-root>.MuiTypography-root.MuiTypography-h6")
    WebElement rulesBoxHeading;
    @FindBy (css = ".MuiBox-root .MuiList-root.MuiList-dense.MuiList-padding")
    WebElement rulesBoxContent;
    @FindBy (css = "[href=\"/\"]")
    WebElement homeButton;
    @FindBy (css = "[name=\"title\"]")
    WebElement titleField;
    @FindBy (css = "[name=\"details\"]")
    WebElement detailsField;
    @FindBy (css = "[name=\"phone\"]")
    WebElement phoneField;
    @FindBy (css = "[name=\"address\"]")
    WebElement addressField;
    //@FindBy (css = ".MuiPaper-root.MuiPaper-elevation1.MuiPaper-rounded input:nth-child(3)")
    @FindBy (css = ".MuiInputBase-input.MuiOutlinedInput-input.MuiAutocomplete-input.MuiAutocomplete-inputFocused.MuiInputBase-inputAdornedEnd.MuiOutlinedInput-inputAdornedEnd")
    WebElement tagField;
    @FindBy (css = "[type=\"submit\"]")
    WebElement postButton;

    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public EmumbaPostFlyerPage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * get rules box title
     * @return returns rules box title in string format
     */
    public String getRulesHeading() {
        return rulesBoxHeading.getText();
    }

    /**
     * get rules box content
     * @return returns rules box content in string format
     */
    public String getRulesContent() {
        return rulesBoxContent.getAttribute("innerText");
    }

    /**
     * clicks on home button
     * @return returns home page object
     */
    public EmumbaHomePage clickHomeButton() {
        homeButton.click();
        return new EmumbaHomePage(driver,waitTime);
    }

    /**
     * enters tags in tag field
     * @param tags list of tags to enter
     */
    private void enterTags(List<String> tags) {
        for(String tag:tags) {
            tagField.click();
            tagField.sendKeys(tag, Keys.ENTER);
        }
    }

    /**
     * posts a new flyer
     * @param title title of the flyer
     * @param details details of the flyer
     * @param phoneNumber phone number for the flyer
     * @param address adress for the flyer
     * @param tags tags for the flyer
     */
    public void postFlyer(String title, String details, String phoneNumber,String address, List<String> tags) {
        titleField.click();
        titleField.sendKeys(title);
        detailsField.click();
        detailsField.sendKeys(details);
        phoneField.click();
        phoneField.sendKeys(phoneNumber);
        addressField.click();
        addressField.sendKeys(address);
        this.enterTags(tags);
        postButton.click();
    }

}
