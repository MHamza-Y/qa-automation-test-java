package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GoogleResultPage {
    WebDriver driver;
    WebDriverWait wait;
    int waitTime;

    @FindBy(css = ".LC20lb.DKV0Md")
    List<WebElement> resultHeadings;

    @FindBy(css = ".r>a")
    List<WebElement> resultLinks;

    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public GoogleResultPage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * Get the result links and headings
     * @return list of map items containing result links and headings
     */
    public List<Map<String,String>> getResults() {
        List<Map<String, String>> resultsList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < resultHeadings.size(); i++) {
            Map<String, String> resultsMap = new HashMap<String, String>();
            resultsMap.put("heading", resultHeadings.get(i).getText());
            resultsMap.put("link", resultLinks.get(i).getAttribute("href"));
            resultsList.add(resultsMap);
        }

        return resultsList;
    }
}
