package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

/**
 * manage new tabs
 */
public class NewTab {
    WebDriver driver;

    public NewTab(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * opens new tab in the browser with given url and switches to the new tab
     * @param url url for the new tab
     */
    public void openNewTab(String url) {
        ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size()-1));
        driver.get(url);
    }
}
