package runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions
(
    features = {"src/test/resources/features"},
    glue = {"stepdefinitions", "hooks"},
    plugin = 
		{
				"pretty",
				"html:target/Negative_Scenarios_cucumber-report/cucumber.html",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				"json:target/Negative_Scenarios_cucumber-report/cucumber.json",
				"rerun:target/Negative_Scenarios_cucumber-report/cucumber.html"
		},
		
		monochrome = true,
		
		// Default execution for Pan Verified User on local         
		//tags="@CreateSIP"   
		
		//Execution for new User/Guest USer
		tags="@PANNegativeScenarios"
		
		// passing tags from commandline for CI execution
		//tags = "${cucumber.filter.tags}"

 
)


public class PANNegativeScenarioRunner extends AbstractTestNGCucumberTests 
{
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
	{
        return super.scenarios();
	}
}

