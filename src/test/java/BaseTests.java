import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import utils.ExcelFile;
import utils.NewTab;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class BaseTests {
    private static WebDriver driver;
    private static NewTab tabs;
    private static int waitTime = 10;

    /**
     * set up new instance of static chromedriver to be shared by all tests
     */
    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        driver = new ChromeDriver();
        tabs = new NewTab(driver);
        System.out.println("Initialized WebDriver");
        System.out.println("Before Suite");
    }

    /**
     * close chromedriver after all tests have finished
     */
    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
    
    public static WebDriver getWebDriver() {
        return driver;
    }
    public static NewTab getNewTabs() {
        return tabs;
    }


    /**
     *
     * This function provides input data for tests. The data is read from an excel file sheet. Sheet is chosen depending on method name
     * @param method method using this data provider is passed as an argument
     * @return returns an iterator object containing the data of input sheet in Map format
     * @throws IOException this exception is thrown if unable to read file
     */
    @DataProvider(name = "input-sheet")
    public static Iterator<Object[]> getSheetData(Method method) throws IOException {
        String sheetName ;
        String methodName = method.getName();
        if(methodName.equals("searchMovie") || methodName.equals("fullCastTest")) {
            sheetName = "Input";
        }
        else
            sheetName = "Rest Assured";


        List<Map<String,String>> sheetVals = ExcelFile.readSheet("src/test/resources/qaautomation.xlsx",sheetName);

        Collection<Object[]> data = new ArrayList<>();
        sheetVals.forEach(item -> data.add(new Object[]{item}));
        return data.iterator();
    }
    public static int getWaitTime() {
        return waitTime;
    }
}
