import org.testng.Assert;
import org.testng.ITestContext;
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
     * @throws IOException an exception is thrown if unable to write to file
     */
    @Test (dataProvider = "input-sheet")
    public void fullCastTest(Map<String,String> sheet,ITestContext context) throws IOException {
        getNewTabs().openNewTab(context.getAttribute("movieUrl").toString());
        ImdbMoviePage moviePage = new ImdbMoviePage(getWebDriver(),BaseTests.getWaitTime());
        ImdbMovieCastPage fullCastPage = moviePage.clickSeeFullCast();
        List<Map<String,String>> castInfo = fullCastPage.getFullCastInfo();

        Assert.assertEquals(
                castInfo.size(),
                Integer.parseInt(sheet.get("castSize"))
        );
        ExcelFile.writeToSheet(castInfo,"src/test/resources/qaautomation.xlsx","Series Cast");
    }

}
