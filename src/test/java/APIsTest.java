import com.google.errorprone.annotations.Var;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.StringUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class APIsTest extends BaseTests {

    /**
     * Sends an API request and validates the reponse
     * @param sheet the sheet containing API request data
     */
    @Test(dataProvider = "input-sheet")
    public void APIsRequestTest(Map<String, String> sheet) {
        RequestSpecification specification ;
        Response response = new RestAssuredResponseImpl();
        String payloadStr = sheet.get("Request Payload (json)").replace("[@here]",""+Math.random());
        String method = sheet.get("Method");
        String api = sheet.get("API");

        if (payloadStr.equals("N/A")){
            payloadStr = "";
        }


        specification = given().body(payloadStr).when();
        if (method.equals("GET")) {
            response = specification.redirects().follow(true).get(api);

        } else if (method.equals("POST")) {
            response = specification.post(api);
        }

       Assert.assertEquals(
                response.statusCode(),
                Integer.parseInt(sheet.get("Expected Response Code")),
                "Response status code do not match"
        ); //assert status code

        Assert.assertTrue(
                Integer.parseInt(response.header("Content-Length"))
                        <=
                        Integer.parseInt(sheet.get("Expected Response Size in kilobyte (less than)"))*1024,
                "Response size greater than expected"
                ); //assert size

        Assert.assertTrue(
                response.timeIn(TimeUnit.SECONDS)
                        <=
                        Integer.parseInt(sheet.get("Expected Response time in seconds (less than)")),
                "Response time greater than expected"
        ); // assert response time

        Assert.assertTrue(
                responseContainsExpectedKeys(
                        response.asString(),
                        sheet.get("Response Contains")
                ),
                "Response does not contain expected keys"
        );
        System.out.println(payloadStr);
        System.out.println(response.header("Content-Length"));
        System.out.println(response.headers());
        System.out.println(response.asString());
    }

    /**
     * Checks if response contains expected elements such as email.
     * @param responseBody received response from API request
     * @param keysArrayAsString expected  keys in response
     * @return returns true if response contains expected keys
     */
    private boolean responseContainsExpectedKeys(String responseBody, String keysArrayAsString) {
        List<String> expectedStrList = StringUtils.parseStringToList(keysArrayAsString);
        for(String item:expectedStrList) {
            if(item.equals("N/A")) {
                return true;
            }
            if(!responseBody.contains(item)) {
                return false;
            }
        }
        return true;
    }
}
