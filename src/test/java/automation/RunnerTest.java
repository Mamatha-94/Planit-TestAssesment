package automation;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","html:target/cucumber-report.html","json:target/cucumber-json/cucumber.json","junit:target/cucumber.xml" },
					features="src/test/java/features",
					glue={"stepdefinition"},
					tags="@tc1 or @tc2 or @tc3 or @tc4",
					monochrome=true)
public class RunnerTest {
	
}

	

