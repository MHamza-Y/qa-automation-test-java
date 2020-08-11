package pages.imdb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class ImdbMovieCastPage {
    WebDriver driver;
    int waitTime;
    WebDriverWait wait;

    @FindBy (css = ".cast_list .primary_photo img")
    List<WebElement> names;
    @FindBy (css = ".character")
    List<WebElement> characterInfo;

    /**
     * constructor
     * @param driver webdriver to initialize page object
     * @param waitTime time to wait for elements to be visible
     */
    public ImdbMovieCastPage(WebDriver driver,int waitTime) {
        this.driver = driver;
        this.waitTime = waitTime;
        this.wait = new WebDriverWait(driver,waitTime);
        PageFactory.initElements(driver, this);
    }

    /**
     * get list for full cast containing name, stage name and appearances
     * @return list containing cast information
     */
    public List getFullCastInfo() {

        wait.until(ExpectedConditions.visibilityOfAllElements(names));
        wait.until(ExpectedConditions.visibilityOfAllElements(characterInfo));

        List<WebElement> tempNames = names;
        List<WebElement> tempCharacterInfo = characterInfo;
        List<Map<String,String>> castInfo = new ArrayList<>();
        Map<String,String> castMap;

        for (int i=0;i<tempNames.size();i++) {
            castMap = new LinkedHashMap<>();

            String[] charText = tempCharacterInfo
                    .get(i)
                    .getAttribute("innerText")
                    .replaceAll("/ ...","")
                    .split("\n");

            castMap.put("Name",names.get(i).getAttribute("title"));
            castMap.put("Stage Name",charText[0]);
            castMap.put("Appearances",charText[1]);


            castInfo.add(castMap);

        }

        return castInfo;
    }
}
