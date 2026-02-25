package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = 	{"@target/Failed_Negative_Scenarios.txt"},
    
    glue = 		{"stepdefinitions", "hooks"},
    
    plugin =    {
			        "pretty",
			        "html:target/Negative_Scenarios_cucumber-report/failed/cucumber.html",
					 "json:target/Negative_Scenarios_cucumber-report/failed/cucumber.json"
			       
    		    },
    		
   	monochrome = true,
   	
   	publish=true
)
public class PANNegativeScenariosFailedTCRunner extends AbstractTestNGCucumberTests 
{
	
}


