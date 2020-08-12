import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.emumba.EmumbaHomePage;
import pages.emumba.EmumbaLoginPage;
import pages.emumba.EmumbaPostFlyerPage;
import pages.emumba.EmumbaRegisterPage;
import utils.StringUtils;


import java.util.Map;

public class EmumbaPostFlyerTest extends BaseTests {

    @Test(dataProvider = "input-sheet")
    public void postFlyerTest(Map<String, String> sheet) {
        EmumbaHomePage homePage = new EmumbaHomePage(BaseTests.getWebDriver(), BaseTests.getWaitTime());

        getNewTabs().openNewTab(homePage.getUrl()); //open a new tab and go to emumba site


        EmumbaLoginPage loginPage = homePage.clickLoginButton(); //go to login page

        EmumbaRegisterPage registerPage = loginPage.clickNotAUserYet(); //goto registration page

        registerPage.register(
                sheet.get("firstname"),
                sheet.get("lastname"),
                sheet.get("Email"),
                sheet.get("Password"),
                sheet.get("Password")); //register a new user with data from excel file

        loginPage.login(sheet.get("Email"), sheet.get("Password"));//login the new user

        EmumbaPostFlyerPage postFlyerPage = homePage.clickPostFlyerButton();//go to post flyer page

        String flyerTitle = postFlyerPage.getRulesHeading();
        String flyerBody = postFlyerPage.getRulesContent()+" "+ Math.random();

        postFlyerPage.postFlyer(
                flyerTitle,
                flyerBody,
                sheet.get("Phone#"),
                sheet.get("Address"),
                StringUtils.parseStringToList(sheet.get("Tags"))
        ); //post a new flyer

        postFlyerPage.clickHomeButton();// go to home page

        String finalFlyerBody = String.join(" ", flyerBody.split("\n"));

        WebDriverWait wait = new WebDriverWait(getWebDriver(),getWaitTime());

        wait.until(driver -> waitForPostLoad(
                finalFlyerBody,
                homePage.getLatestPostBody()
        )
    );

        //check if post was successful
        Assert.assertEquals(
                homePage.getLatestPostHeading(),
                flyerTitle,
                "Post title do not match");
        Assert.assertEquals(
                homePage.getLatestPostBody(),
                finalFlyerBody,
                "Flyer body do not match");

        homePage.clickLogoutButton(); //logout of the emumba site
    }

    private boolean waitForPostLoad(String expectedElementText,String actualElementText) {
        getWebDriver().navigate().refresh();
        return expectedElementText.equals(actualElementText);
    }

}
