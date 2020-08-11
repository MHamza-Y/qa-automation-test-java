import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.imdb.ImdbMovieCastPage;
import pages.imdb.ImdbMoviePage;
import utils.ExcelFile;

import java.io.IOException;
import java.util.*;

public class ImdbFullCastTest extends BaseTests{

    /**
     * Clicks see full cast and checks if full cast is shown.
     * @param sheet sheet data in Key Value form
     * @param context to get link saved on previous step
     * @throws IOException
     */
    @Test (dataProvider = "input-sheet")
    public void fullCastTest(Map<String,String> sheet,ITestContext context) throws IOException {
        getNewTabs().openNewTab(context.getAttribute("movieUrl").toString());
        ImdbMoviePage moviePage = new ImdbMoviePage(getWebDriver(),BaseTests.getWaitTime());
        ImdbMovieCastPage fullCastPage = moviePage.clickSeeFullCast();
        List<Map<String,String>> castInfo = fullCastPage.getFullCastInfo();

        int castSize = Integer.parseInt(sheet.get("castSize"));
        Assert.assertEquals(
                castInfo.size(),
                Integer.parseInt(sheet.get("castSize"))
        );
        ExcelFile.writeToSheet(castInfo,"src/test/resources/qaautomation.xlsx","Series Cast");
    }

}
