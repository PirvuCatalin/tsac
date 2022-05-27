package ro.catalinpirvu.tsac;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@CucumberContextConfiguration
@SpringBootTest(classes = TsacApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefsIntegrationMocking extends SpringIntegrationMocking {

    @When("^the client calls /baeldung$")
    public void the_client_issues_POST_hello() throws Throwable {
        executePost();
    }

    @Given("^the client calls /hello$")
    public void the_client_issues_GET_hello() throws Throwable {
        executeGet("http://localhost:8080/hello");
    }

    @When("^the client calls /getRandomNumber$")
    public void the_client_issues_GET_getRandomNumber() throws Throwable {
        executeGet("http://localhost:8080/getRandomNumber");
    }

    @When("^the client calls /getRandomNumber with upperbound (.*)$")
    public void the_client_issues_GET_getRandomNumber_with_upperbound_limit(int upperbound) throws Throwable {
        executeGet("http://localhost:8080/getRandomNumber?upperBound=" + upperbound);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives a random number$")
    public void the_client_receives_a_random_number() {
        assertThat(latestResponse.getBody(), notNullValue());
    }

    @And("^the client receives a random number with upper bound of (\\d+)$")
    public void the_client_receives_a_random_number_with_upperbound(int upperBound) {
        assertThat(Integer.parseInt(latestResponse.getBody()), lessThan(upperBound));
        assertThat(Integer.parseInt(latestResponse.getBody()), greaterThanOrEqualTo(0));
    }

    @And("^the client receives an error message that the upperbound is not valid$")
    public void the_client_receives_an_error_message_that_upperbound_is_invalid() {
        assertThat(latestResponse.getBody(), equalTo("Upper bound must be a positive integer!"));
    }
}