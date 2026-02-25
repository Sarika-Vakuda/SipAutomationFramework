
	
	package runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
	import io.cucumber.testng.CucumberOptions;

	
	@CucumberOptions(
	    features = 	{"@target/failed_scenarios.txt"},
	    
	    glue = 		{"stepdefinitions", "hooks"},
	    
	    plugin =    {
				        "pretty",
				        "html:target/cucumber-report/failed/cucumber.html",
						 "json:target/cucumber-report/failed/cucumber.json"
				       
	    		    },
	    		
	   	monochrome = true,
	   	
	   	publish=true
	)
	public class FailedTCRunner extends AbstractTestNGCucumberTests 
	{
		
	}
	

	