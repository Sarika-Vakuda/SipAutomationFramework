package factory.manager;



import java.net.MalformedURLException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



	public class DriverManager {
		
		private static final Logger log = LogManager.getLogger(DriverManager.class);
		
		// ThreadLocal for safe parallel execution
		private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	 
	    public static WebDriver initBrowser(String browserName,String remoteUrl) {
	    	
	    	
	    	 // Safe default if browserName is null or empty
	        if (browserName == null || browserName.trim().isEmpty())
	        {
	            log.info("Browser name not provided. Launching Chrome as default.");
	           
	            browserName = "chrome";
	        }
	        
	        try {
	            if (remoteUrl != null && !remoteUrl.isEmpty())
	            {
	            	log.info("Remote URL detected: " + remoteUrl + " - Running tests remotely.");
	                // Remote Execution
	                log.info("Initializing Remote WebDriver on " + remoteUrl);
	               
	                DesiredCapabilities capabilities = new DesiredCapabilities();

	                switch (browserName.toLowerCase().trim())
	                {
	                    case "chrome": capabilities.setBrowserName("chrome");
	                                     break;
	                   
	                    case "firefox": capabilities.setBrowserName("firefox");
	                                     break;
	                   
	                    case "edge": capabilities.setBrowserName("edge");
	                                     break;
	                 
	                         default: capabilities.setBrowserName("chrome");
	                                     break;
	                }

	                driver.set(new RemoteWebDriver(new URL(remoteUrl), capabilities));
	            }
	           
	            else 
	            {
	              
	            	 log.info("No remote URL detected. Running tests locally.");
			         
	            	 // Local Execution
			        // Initialize browser based on name
			        if (browserName.trim().equalsIgnoreCase("chrome")) 
			        {
			        	log.info("Setting up Chrome Browser.");
			            
			        	WebDriverManager.chromedriver().setup();
		
			            ChromeOptions options = new ChromeOptions();
			            
			            // options.addArguments("--headless=new"); // enable in CI if needed
			            
			            options.addArguments("--incognito");
		
			            driver.set(new ChromeDriver(options));
			            
			        }
			        else if (browserName.trim().equalsIgnoreCase("firefox"))
			        {
			        	log.info("Setting up Firefox Browser.");
			        	
			        	WebDriverManager.firefoxdriver().setup();
			           
			            FirefoxOptions options=new FirefoxOptions();
				       
			            options.addArguments("-private");
			           
			            driver.set(new FirefoxDriver(options));
			        }
			        
			        else if (browserName.trim().equalsIgnoreCase("edge")) 
			        {
			        	log.info("Setting up Edge Browser.");
			        	
			        	WebDriverManager.edgedriver().setup();
			           
			           EdgeOptions options=new EdgeOptions();
			           
			           options.addArguments("--inprivate");
			            
			            driver.set(new EdgeDriver(options));
			        }
			        
			        else
			        {
			        	log.info("Browser not recognized : " + browserName + ". Launching Chrome as default");
			        	
			            WebDriverManager.chromedriver().setup();
			            
			            ChromeOptions options = new ChromeOptions();
			            
			            // options.addArguments("--headless=new"); // enable in CI if needed 
			            options.addArguments("--incognito");
			           
			            driver.set(new ChromeDriver(options));
			        }
	             }
	           }
	            
	            catch (MalformedURLException e) 
	            {
	                log.error("Invalid Remote WebDriver URL: " + remoteUrl);
	                e.printStackTrace();
	            }

			        return getDriver();
	            
	    }

	    public static WebDriver getDriver() 
	    {
	    	return driver.get();
	    }
	    
	    public static void quitDriver() 
	    {
	        if(driver.get() != null)
	        {
	            
	        	driver.get().quit();
	            
	        	driver.remove();
	       }
        }
}
	
