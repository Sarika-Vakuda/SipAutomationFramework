package runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.manager.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleWrapper;
import reports.ExtentTestManager;
import utils.LogUtil;
import utils.PropReader;



@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {
		                "pretty",
		                "html:target/cucumber-report/cucumber.html",
		                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
		                "json:target/cucumber-report/cucumber.json"
        		 },
        monochrome = true,
        		
        		//Default execution for Pan Verified User on local         
     			// tags="@CreateSIP"   
     			
     			//Execution for new User/Guest USer
     			 tags="@PAN"
     			
     			// passing tags from commandline for CI execution
     			// tags = "${cucumber.filter.tags}"

)
public class RetryRunner extends AbstractTestNGCucumberTests 
{

	private static final int MAX_RETRIES = Integer.parseInt(PropReader.getProperty("retry.count"));

    @Override
    @Test(dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, io.cucumber.testng.FeatureWrapper featureWrapper) 
    {
        int retryAttempt = 0;
       

        while (retryAttempt <= MAX_RETRIES) 
        {
        	if (retryAttempt > 0)
        	{
        		DriverManager.quitDriver();
        		
        		LogUtil.log.info("closed the browser for retrying the scenario");
        	}
            try
            {
                super.runScenario(pickleWrapper, featureWrapper); // run the scenario
                
                return; // success, exit
            } 
            catch (Throwable t)
            {
                //  Business assertion failure → NO RETRY
                if (t instanceof AssertionError)
                {
                    LogUtil.log.error("Business assertion failed. Skipping retry for scenario: " + pickleWrapper.getPickle().getName());

                    if (ExtentTestManager.getTest() != null)
                    {
                        ExtentTestManager.getTest().fail("Business assertion failure: " + t.getMessage());
                    }

                    throw new RuntimeException(t); // immediately fail
                }
           
            	retryAttempt++;
                
            	LogUtil.log.info("Retrying scenario: " + pickleWrapper.getPickle().getName() + " | Attempt " + retryAttempt);
               
            	if (retryAttempt >= MAX_RETRIES) 
                {
                    // Log failure for ExtentReports
                    if (ExtentTestManager.getTest() != null) 
                    {
                    	LogUtil.log.info("Scenario failed after " + MAX_RETRIES + " retries: " + t.getMessage());
                        ExtentTestManager.getTest().fail("Scenario failed after " + MAX_RETRIES + " retries: " + t.getMessage());
                    }
                    // TestNG mark it as failed
                    throw new RuntimeException(t);
                }
            }
        }
        
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
    
    
 