import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.emumba.EmumbaHomePage;
import pages.emumba.EmumbaLoginPage;
import pages.emumba.EmumbaPostFlyerPage;
import pages.emumba.EmumbaRegisterPage;
import utils.NewTab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class EmumbaPostFlyerTest extends BaseTests{

    @Test (dataProvider = "input-sheet")
    public void postFlyerTest(Map<String,String> sheet) throws InterruptedException {
        EmumbaHomePage homePage = new EmumbaHomePage(BaseTests.getWebDriver(),BaseTests.getWaitTime());

        getNewTabs().openNewTab(homePage.getUrl());
        EmumbaLoginPage loginPage = homePage.clickLoginButton();
        EmumbaRegisterPage registerPage = loginPage.clickNotAUserYet();
        registerPage.register(
                sheet.get("firstname"),
                sheet.get("lastname"),
                sheet.get("Email"),
                sheet.get("Password"),
                sheet.get("Password"));
        loginPage.login(sheet.get("Email"),sheet.get("Password"));
        EmumbaPostFlyerPage postFlyerPage = homePage.clickPostFlyerButton();
        postFlyerPage.postFlyer(
                postFlyerPage.getRulesHeading(),
                postFlyerPage.getRulesContent(),
                sheet.get("Phone#"),
                sheet.get("Address"),
                Arrays.asList(
                        sheet.get("Tags")
                                .replaceAll("\\[", "")
                                .replaceAll("\\]", "")
                                .replaceAll("\"", "")
                                .split(",")
                ));

    }


}
