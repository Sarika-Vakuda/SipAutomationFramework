	
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
					"html:target/cucumber-report/cucumber.html",
					"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
					"json:target/cucumber-report/cucumber.json",
					"rerun:target/failed_scenarios.txt"
			},
			
			monochrome = true,
			
			// Default execution for Pan Verified User on local         
			//tags="@CreateSIP"   
			
			//Execution for new User/Guest USer
			tags="@PAN"
			
			// passing tags from commandline for CI execution
			//tags = "${cucumber.filter.tags}"

	 
	)
	public class Runner extends AbstractTestNGCucumberTests {
		@Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios()
		{
	        return super.scenarios();
	}
	}

	