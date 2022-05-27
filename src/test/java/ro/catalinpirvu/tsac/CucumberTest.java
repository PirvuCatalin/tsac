package ro.catalinpirvu.tsac;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest extends SpringIntegrationMocking {
    @Test
    public void test() {
    }
}
