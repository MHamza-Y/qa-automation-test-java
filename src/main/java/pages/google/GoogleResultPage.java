package pages.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleResultPage {
    WebDriver driver;

    @FindBy(css = ".LC20lb.DKV0Md")
    List<WebElement> resultHeadings;

    @FindBy(css = ".r>a")
    List<WebElement> resultLinks;

    public GoogleResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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
