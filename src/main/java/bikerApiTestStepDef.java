import com.mashape.unirest.http.exceptions.UnirestException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class bikerApiTestStepDef {

    @When("^I hit the biker Api$")
    public void getAPIrequest() throws UnirestException {
        bikerApiTest.getcitybikeinfo();

    }

    @Then("^I should get valid response code as 200$")
    public void i_should_see_the_available_products() throws Throwable {
        Assert.assertTrue("Status is not 200",bikerApiTest.validateResponseCode());
    }

    @Then("^the country code for (.*) city should be (.*), Germany$")
    public void validateCountryCode(String city, String code) throws UnirestException {

        Assert.assertTrue("Country code does not match",bikerApiTest.validatecountrycodeforcity(city, code));
       }

    @Then("^the latitude and longitude for (.*) should be retrieved$")
    public void getLattitudeLongitude(String city) throws UnirestException {

        Assert.assertTrue("Latitude and Longitude values are not fetched",bikerApiTest.getLatLongforCity(city));
    }

}
