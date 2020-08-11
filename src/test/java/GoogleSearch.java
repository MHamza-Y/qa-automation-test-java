import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.google.GoogleHomePage;
import pages.google.GoogleResultPage;
import utils.ExcelFile;

import java.io.IOException;
import java.util.*;


public class GoogleSearch extends BaseTests{
    /**
     * Page object for the google home page
     */
    GoogleHomePage homePage;
    /**
     * Page object for the google results page
     */
    GoogleResultPage resultPage;

    /**
     * This test searches for a movie and asserts if expected result is found
     * @param sheet variable containing input sheet data in key value format passed from a data provider
     */
    @Test(dataProvider = "input-sheet")
    public void searchMovie(Map<String,String> sheet)  {
        homePage = new GoogleHomePage(BaseTests.getWebDriver());
        homePage.goTo();
        resultPage = homePage.search(sheet.get("Search String 1"));
        String resultLink = searchListForTitleString( resultPage.getResults(),sheet.get("Search String 2"));

        Assert.assertNotEquals(resultLink,"");

    }

    /**
     * This function searches list elements heading for the search string and if found returns the link
     * @param links Map containing headings and corresponding links
     * @param searchKey Key to search for in the headings Map
     * @return if match is found in headings its link is returned else an empty string is returned
     */
    private String searchListForTitleString(List<Map<String,String>> links,String searchKey) {
        for (Map<String,String> link : links){
            if(link.get("heading").contains(searchKey)) {
                return link.get("link");
            }
        }
        return "";
    }


    /**
     * This function provides input data for the search movie test. The data is read from an excel file sheet.
     * @return returns an iterator object containing the data of input sheet in Map format
     * @throws IOException this exception is thrown if unable to read file
     */
    @DataProvider (name = "input-sheet")
    public Iterator<Object[]> getInputs() throws IOException {
        ExcelFile excelFile = new ExcelFile();
        List<Map<String,String>> sheetVals = excelFile.readSheet("src/test/resources/qaautomation.xlsx","Input");

        Collection<Object[]> data = new ArrayList<Object[]>();
        sheetVals.forEach(item -> data.add(new Object[]{item}));
        return data.iterator();
    }
}
