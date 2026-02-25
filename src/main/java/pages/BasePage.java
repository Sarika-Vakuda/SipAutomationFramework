
package pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


	public class BasePage
	{
		private static Logger log=LogManager.getLogger(BasePage.class);
	    protected WebDriver driver;
	    protected WebDriverWait wait;
	    protected Actions actions;
	   
	    public BasePage(WebDriver driver) 
	    {
	        this.driver =driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        this.actions = new Actions(driver);
	    }
	    
	    public void clearAndSendKeys(WebElement element, String value)
	    {
	    	for (int i = 0; i < 3; i++) 
			   {
		            try
		            {
		            	wait.until(ExpectedConditions.visibilityOf(element));
		                
		            	click(element);
		    	    	
		    	    	element.clear();
		    	    	
		    	        element.sendKeys(value);
		                
		    	        return;
		            } 
		            catch (StaleElementReferenceException e)
		            {
		                log.warn("Retrying sendKeys due to stale element: " + element);
		            }
		        }
		        throw new RuntimeException("Unable to send keys: " + element);
	    	
	    }

	    public void click(WebElement element)
	    {
	    	try
	    	  {
	    	     waitForElementToBeClickable(element).click();
	    	  } 
	    	catch (ElementClickInterceptedException e)
	    	{
	            
	    		log.warn("Click intercepted, falling back to JS click");
	           
	    		jsClick(element);
	        } 
	    	catch (Exception e)
	    	{
	            throw e;
	        }
	    }

	    public void clickOnVisibility(WebElement element) 
	    {
	    	
	    		waitForElementVisibility(element).click();
	    	
	    }

	    public WebElement waitForElementVisibility(WebElement element)
	    {
	       
	    	
	    		return wait.until(ExpectedConditions.visibilityOf(element));
	    	
	    }
	    
	    
	    protected boolean waitForVisibility(WebElement element) 
	    {
	        try 
	         {
	        	waitForElementVisibility(element);
	         
	            return true;
	         } 
	        catch (TimeoutException e)
	         {
	             return false;
	         }
	    }

	    
	    public WebElement waitForElementToBeClickable(WebElement element) 
	    {
	       
	     	    return wait.until(ExpectedConditions.elementToBeClickable(element));
	        
		  
	    }

	    public void jsClick(WebElement element)
	    {
	    	
		    	waitForElementVisibility(element);
		        
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        
		        js.executeScript("arguments[0].click();", element);
		     
	    }

	    public void scrollToElement(WebElement element)
	    {
	    		JavascriptExecutor js = (JavascriptExecutor) driver;
		        
		        js.executeScript("arguments[0].scrollIntoView(true);", element);
		   
	    }
	    
	    public void moveToElement(WebElement element)
	    {
	    	
	    		   waitForElementVisibility(element);
	    		   
	    		   actions.moveToElement(element).perform();
	          
	    }
	    public boolean isElementPresent(WebElement element)
	    {
	        try
	        {
	            wait.until(ExpectedConditions.visibilityOf(element));
	            
	            return true;
	        } 
	        catch (TimeoutException e) 
	        {
	            return false;
	        }
	    }
	    
	   public void doubleClick(WebElement element)  
	     {
		  
               waitForElementToBeClickable(element);
               
               actions.doubleClick(element).perform();
         
	     }
	   public void selectFromDropdown(WebElement dropdown, List<WebElement> options, String value)
	   {
		    jsClick(dropdown);

		    wait.until(ExpectedConditions.visibilityOfAllElements(options));

		    for (WebElement option : options)
		    {
		        if (option.getText().equalsIgnoreCase(value)) 
		        {
		            jsClick(option);
		            
		            return;
		        }
		    }

		    throw new RuntimeException("Option not found: " + value);
		}

	   public int extractNumber(WebElement element)
	   
	   {
		    return Integer.parseInt(waitForElementVisibility(element)
		    			  .getText()
		    			  .replaceAll("[^0-9]", ""));
		}
	   
	   //=============================== handling locators =====================================
	   
	   public void clearAndSendKeys(By locator, String value)
	   	{
		   for (int i = 0; i < 3; i++) 
		   {
	            try
	            {
	                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	                element.clear();
	                element.sendKeys(value);
	                return;
	            } 
	            catch (StaleElementReferenceException e)
	            {
	                log.warn("Retrying sendKeys due to stale element: " + locator);
	            }
	        }
	        throw new RuntimeException("Unable to send keys: " + locator);
		}

		public void click(By locator)
		{
			for (int i = 0; i < 3; i++) 
			{
	            try
	            {
	                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	                element.click();
	                return;
	            } 
	            catch (StaleElementReferenceException e) 
	            {
	                log.warn("Retrying click due to stale element: " + locator);
	            }
	        }
	        throw new RuntimeException("Unable to click element: " + locator);
		}
	/*	public void safeClick(By locator) {
		    int attempts = 0;

		    while (attempts < 3) {
		        try {
		            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		         
		            return;
		        } 
		        catch (StaleElementReferenceException e) 
		        {
		            attempts++;
		        } 
		        catch (ElementClickInterceptedException e)
		        {
		            jsClick(wait.until(ExpectedConditions.presenceOfElementLocated(locator)));
		            return;
		        }
		    }
		    throw new RuntimeException("Unable to click element: " + locator);
		}*/
		public void safeClick(By locator)
		{
		    int attempts = 0;

		    while (attempts < 3)
		    {
		        try
		        {
		            // Wait directly for element to be clickable (includes presence + visibility + enabled)
		            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

		            // Scroll to center view
		            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);

		            // Click the element
		            element.click();

		            return; // success, exit method

		        } 
		        catch (StaleElementReferenceException | TimeoutException e)
		        {
		            // Stale element or timeout waiting -> retry
		            attempts++;
		            sleep(500);
		        }
		        catch (ElementClickInterceptedException e) 
		        {
		            // Possibly overlay or animation blocking click - try JS click after short wait
		            attempts++;
		            sleep(500);
			            try
			            {
			                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			                jsClick(element);
			                return;
			            } 
			            catch (Exception jsException)
			            	{
			            		// If JS click also fails, continue retrying
			            	}
		        } 
		        
		        catch (Exception e)
		        {
		            	// Unexpected exceptions - fail fast
		        		throw new RuntimeException("Unable to click element: " + locator, e);
		        }
		    }
		    
		   throw new RuntimeException("Unable to click element after retries: " + locator);
		}

		// Helper method for sleep
		private void sleep(long millis)
		{
		    try 
		    {
		        Thread.sleep(millis);
		    } 
		    catch (InterruptedException ignored)
		    {
		    }
		}
		

		// Your existing JS click helper
		
		public WebElement waitForElementVisibility(By locator) 
		{
		    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		public WebElement waitForElementToBeClickable(By locator)
		{
		    return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		 public boolean isElementPresent(By locator)
		 {
		        try
		        {
		            return waitForElementVisibility(locator).isDisplayed();
		        } 
		        catch (Exception e)
		        {
		            return false;
		        }
		    }
		
		 
		   

		    // ================= JS CLICK (Angular fallback) =================

		    public void jsClick(By locator) 
		    {
		        WebElement element = waitForElementVisibility(locator);
		        JavascriptExecutor js=((JavascriptExecutor) driver);
		        js.executeScript("arguments[0].click();", element);
		    }

		    // ================= SCROLL =================

		    public void scrollTo(By locator)
		    {
		        WebElement element = waitForElementVisibility(locator);
		        JavascriptExecutor js=((JavascriptExecutor) driver);
		        js.executeScript("arguments[0].scrollIntoView(true);", element);
		    }

		    // ================= IFRAME =================

		    public void switchToFrame(By locator)
		    {
		        WebElement iframe = waitForElementVisibility(locator);
		        driver.switchTo().frame(iframe);
		    }

		//  =================Handiling Razorpay iframe======================== 
		
	    public void switchToRazorpayFrame(By locator) 
	    {
	    		//Switch to iframe
	    	
	            WebElement iframe = waitForElementVisibility(locator);
	            
	            driver.switchTo().frame(iframe);
	    }
	   
	 // ================= WINDOW HANDLING =================

	   public String getCurrentURL()
	   {
			return driver.getCurrentUrl();
	   }
	   
	   public String getPageTitle()
	   {
			return driver.getTitle();
	   }
	    public String getCurrentWindow() 
	    {
	        return driver.getWindowHandle();
	    }
	   
	    public void switchToNewWindow() 
	    {
	    	String parentWindow=getCurrentWindow() ;
	    	
	    	// Wait for Razorpay window
	        wait.until(d -> driver.getWindowHandles().size() > 1);
	        
	        // Switch to Razorpay window
	        Set<String> allWindows=driver.getWindowHandles();
	        
	        for (String childWindow: allWindows)
	        	{
	        		if (!childWindow.equals(parentWindow))
	        		{
	        			wait.until(d ->driver.switchTo().window(childWindow));
	        			
	        			break;
	        		}
	        		
	        	}
	  
	      }
	    
	    public void waitForOnlyParentWindow()
	    {
	        wait.until(d -> driver.getWindowHandles().size() == 1);
	    }

	    public void switchBackToParent(String parentWindow)
	    {
	    	driver.switchTo().window(parentWindow);
	    }

	   
	    //=====================Retry Logic============================================
	    
	    public void retryingClickOnRazorPayWindow(By locator, int maxAttempts) 
	    {
	        int attempts = 0;
	        
	        while (attempts < maxAttempts) 
	        {
	            try
	            {
	                waitForElementToBeClickable(locator).click();
	                
	                return;   
	            } 
	            
	            catch (Exception e)
	            {
	                attempts++;
	                
	                if (attempts == maxAttempts)
	                {
	                    //throw e;
	                	e.printStackTrace();
	                }
	            }
	        }
	    }
	 
	}

	
	    
	
	