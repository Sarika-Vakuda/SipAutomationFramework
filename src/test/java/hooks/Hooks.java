
	package hooks;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import factory.manager.DriverManager;
import factory.manager.PageObjectManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import reports.ExtentTestManager;
import utils.LogUtil;
import utils.PropReader;
import utils.TestContext;
import utils.TimeUtil;


	public class Hooks {
		private static final Logger log = LogManager.getLogger(Hooks.class);
		
		private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/src/test/resources/screenshots/";
	    @Before
	    public  void launchBrowser(Scenario scenario) throws IOException
	    {

	    	
	    	// Read browser from Jenkins parameter or config
	        String browserName = System.getProperty("browser");   // Jenkins param
	        
	        if (browserName == null)
	        {
	            browserName = PropReader.getBrowser();   // config param
	        }
	    
	       
	        // Read remote URL from Jenkins or config
	        String remoteUrl = System.getProperty("remoteUrl");   // Jenkins param
	        
	        if (remoteUrl == null)
	        {
	            remoteUrl = PropReader.getRemoteURL();   // config param
	        }

	        WebDriver driver = DriverManager.initBrowser(browserName, remoteUrl);
	        
	        driver.manage().window().maximize();
	        
	        driver.manage().deleteAllCookies();
	        
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	        
	        driver.get(PropReader.getEnvURL());

	        // Start Extent Test
	        ExtentTestManager.startTest("Cucumber Scenario Execution Summary", scenario.getName());
	       
	        ExtentTestManager.getTest().info("Browser launched : " + browserName);
	       
	        if (remoteUrl != null && !remoteUrl.isEmpty()) 
	        {
	            ExtentTestManager.getTest().info("Running on Remote URL : " + remoteUrl);
	        }
	       
	    }
	    @After(order = 2)
	    public void takeScreenshotOnFailure(Scenario scenario) 
	    {
	           	WebDriver driver=DriverManager.getDriver();
	           	
	           	ExtentTestManager.getTest().info(scenario.getName()+ " Scenario Execution Completed " );
	           
	           	if (scenario.isFailed()) 
	            {
	                try
	                {   
	                	// Attach the screenshots in the reports
	                    TakesScreenshot ts = (TakesScreenshot) driver;
	                    
	                    byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
	                    
	                    scenario.attach(screenshot, "image/png", scenario.getName().replaceAll(" ", "_"));
	                    
	                    //Attach the screenshots in the project as per the path provided 
	                    File sourceFile = ts.getScreenshotAs(OutputType.FILE);
	                   
	                    File destFile = new File(SCREENSHOT_PATH + scenario.getName().replaceAll(" ", "_") + "_" + TimeUtil.getTimeStamp() + ".png");
	                   
	                    FileHandler.copy(sourceFile, destFile);

	                   log.error("Scenario failed. Screenshot captured: " + destFile.getAbsolutePath());
	                    
	                    ExtentTestManager.getTest().fail("Scenario failed, screenshot attached: " + destFile.getAbsolutePath());
	                } 
	                catch (IOException e)
	                {
	                   log.error("Error while taking screenshot: " + e.getMessage());
	                    
	                  //  ((Logger) ExtentTestManager.getTest()).error("Error taking screenshot: " + e.getMessage());
	                   log.error("Error taking screenshot: " + e.getMessage());
	                }
	            }
	            else
	            {
	               log.info("Scenario passed: " + scenario.getName());
	                
	                ExtentTestManager.getTest().pass( scenario.getName()+" Scenario passed");
	            }
	           	TestContext.clear();
	        }
	@After(order = 1)
	    public void CloseApp(Scenario scenario)
	    {
		 try 
		 {
			 if (!scenario.isFailed()) 
	         {
				
				  log.info("Closing the Application.....");
		    	
				   PageObjectManager pom=new PageObjectManager(DriverManager.getDriver());
		    	
				
					if (pom.getHomePage().isDashBoardPageDisplayed())
					{
						pom.getHomePage().clickOnLogoutButton();
				    	
						pom.getHomePage().clickOnconfirmLogoutButton(); 
					}
		    	
					LogUtil.log.info("Application closed successfully!");
				 }
		 }
			 catch(Exception e)
			 {
				
				log.info("Scenario Failed... Skiped Logout", e); 
			 }
	      
	    }

	    @After(order = 0)
	    public void quitBrowser(Scenario scenario)
	    {
	    	try
	    	{
	    		LogUtil.log.info("Closing the browser...");
			
	    		DriverManager.quitDriver();
	    	
	    		//TestContext.clear();
	    	 
	    		LogUtil.log.info("Browser closed successfully!");
	    	}
	    	catch (Exception e)
	    	{
	    		log.warn("Browser quit failed", e);
	        }
	    }
	}

		