import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTests {
    private static WebDriver driver;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Initialized WebDriver");
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }
    
    public static WebDriver getWebDriver() {
        return driver;
    }
}
