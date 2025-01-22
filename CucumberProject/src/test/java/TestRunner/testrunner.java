package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"StepDefs"},
    tags = "@TicketAnalyzer",
    plugin = {"pretty", "html:target/htmlReport.html"}
)
public class testrunner {
	
}

//tags = "@CodeAnalyzer or @TicketAnalyzer"
