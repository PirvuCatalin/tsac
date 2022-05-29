package ro.catalinpirvu.tsac;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import ro.catalinpirvu.tsac.model.Produs;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@CucumberContextConfiguration
@SpringBootTest(classes = TsacApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefsIntegrationMocking extends SpringIntegrationMocking {

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

    @When("^clientul cauta un produs cu numele (.*)$")
    public void clientul_cauta_un_produs_dupa_nume(String nume) {
        executeGet("http://localhost:8080/produse/cauta-produs?nume=" + nume);
    }

    @When("^clientul creeaza un produs cu numele '(.*)' si costul '([\\d\\.]+)'$")
    public void clientul_creaaza_un_produs_cu_nume_si_cost(String nume, Float cost) throws IOException {
        executePost(String.format("http://localhost:8080/produse/creeaza-produs?nume=%s&cost=%f", nume, cost));
    }

    @And("^clientul va primi un produs cu numele '(.*)' si costul '([\\d\\.]+)'$")
    public void clientul_primeste_un_produs_cu_nume_si_cost(String nume, Float cost) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Produs produs = mapper.readValue( latestResponse.getBody(), Produs.class);
        assertThat(produs.getCost(), is(cost));
        assertThat(produs.getNume(), is(nume));
    }

    @When("^clientul cere costul total as cosului de cumparaturi$")
    public void clientul_cere_costul_total_al_cosului_de_cumparaturi() {
        executeGet("http://localhost:8080/cos-de-cumparaturi/cost-total");
    }

    @When("^clientul cere componenta cosului de cumparaturi$")
    public void clientul_cere_componenta_cosului_de_cumparaturi() {
        executeGet("http://localhost:8080/cos-de-cumparaturi/componenta");
    }

    @When("^clientul scoate produsul (.*) din cosul de cumparaturi$")
    public void clientul_scoate_produsul_din_cosul_de_cumparaturi(String nume) {
        executeGet("http://localhost:8080/cos-de-cumparaturi/scoate-produs?nume=" + nume);
    }

    @When("^clientul adauga produsul (.*) cu cantitatea (.*) avand costul '(.*)' in cosul de cumparaturi$")
    public void clientul_adauga_produsul_in_cosul_de_cumparaturi(String nume, Integer cantitate, Float cost) throws IOException {
        executePost(String.format("http://localhost:8080/produse/creeaza-produs?nume=%s&cost=%f", nume, cost));
        executePost(String.format("http://localhost:8080/cos-de-cumparaturi/adauga-produs?nume=%s&cantitate=%d", nume, cantitate));
    }

    @Then("^clientul va primi un code de stare egal cu (\\d+)$")
    public void clientul_primeste_un_cod_de_stare(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @Then("^clientul va primi un cost total egal cu '(.*)'$")
    public void clientul_primeste_un_cost_total(String costTotal) {
        assertThat(latestResponse.getBody(), is(costTotal));
    }

    @Then("^clientul va primi o componenta goala$")
    public void clientul_primeste_componenta_goala() {
        assertThat(latestResponse.getBody(), is("{}"));
    }

    @And("^clientul va primi un mesaj ce contine '(.*)'$")
    public void clinetul_primeste_mesaj_egal_cu(String mesaj) {
        assertThat(latestResponse.getBody(), containsString(mesaj));
    }
}