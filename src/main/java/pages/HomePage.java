package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utils.PropReader;

public class HomePage extends BasePage 
	{

	 private static final Logger log = LogManager.getLogger(HomePage.class);
	 
	@FindBy(xpath = "//input[contains(@id,'mobileNumber')]")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//parent::p-button//button[contains(@type,'submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//h1[contains(text(),'Enter 6-digit security PIN')] ")
    private WebElement enterSecurityPinText;
  
    @FindBy(xpath = "//h1[text()='OTP Verification']")
    private WebElement OTPVerificationText;
  
    @FindBy(xpath = "//p-inputotp//input[@inputmode='tel']")
    private WebElement verifyOtpInput;
    
    @FindBy(xpath = " //button[@type='submit']")
    private WebElement verifyButton;
 
    @FindBy(xpath = "//h1[contains(text(),'Set up your 6-digit security PIN')]")
    private WebElement setUPYourPINtext;
    
    
    @FindBy(xpath ="//p-inputotp[@id='pin']//input[@inputmode='tel']")
    private List<WebElement> enterPin;
  
    
    @FindBy(xpath ="//p-inputotp[@id='renterPin']//input[@inputmode='tel']")
    private List<WebElement> reEnterPin;
    
    private By proceedButton= By.xpath("//button//span[text()='Proceed']") ;
      
    @FindBy(xpath = "//div[text()='Personalisation Questions']")
    private WebElement personalisationQuestionsText;

    private By skipButton=By.xpath("//span[text()='Skip']"); 
   
    @FindBy(xpath = "//h1[text()='Welcome to Augmont!']")
    private WebElement WelcomeText;
    
    @FindBy(xpath = "//span[text()='Buy Digi Gold']")
    private WebElement BuyDigiGoldPageText; 

    @FindBy(xpath = "//span[contains(text(),'Digital Gold ')]")
    private WebElement digitalGoldButton;

    @FindBy(xpath = "(//a[text()='Digital Silver'])[2]")
    private WebElement digitalSilverButton;
    
    @FindBy(xpath = "//button[@type='button']//span[@class='ellipsis']")
    private WebElement userButton;
  
    @FindBy(xpath = "//span[text()='Logout']")
    private WebElement logoutButton;
    
    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    private WebElement confirmlogoutButton; 

    @FindBy(xpath = "(//button[@type='button'])[1]")
    private WebElement logoutInitiationButton; 
    
   
    
    public HomePage(WebDriver driver) 
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterMobileNumber(String mobileNumber)
    {
        clearAndSendKeys(mobileNumberInput, mobileNumber);
    }

    public void clickSubmitButton()
    {
        click(submitButton);
    }

    
    public void enterOTP() 
    {
        click(verifyOtpInput);
        
        clearAndSendKeys(verifyOtpInput, PropReader.getPassword());
    }
    public boolean isVerifyPINTextPresent() 
    {
       return isElementPresent(setUPYourPINtext);
    	
    }
    public boolean isOTPVerificationTextPresent() 
    {
       return isElementPresent(OTPVerificationText);
    	
    }
    public boolean isEnterSecurityPinTextPresent() 
    {
       return isElementPresent(enterSecurityPinText);
    	
    }
    
    public void enterPin() 
    {
    		
    	  
    	 String otp=PropReader.getPassword();
    	
    	    for (int i=0;i<otp.length();i++) 
	    	
    		{
    		 enterPin.get(i).sendKeys(String.valueOf(otp.charAt(i)));
	            
	        }
    		
    	}
    
    public void reEnterPin() 
    {
    	
    	String otp=PropReader.getPassword();
	    	
    	for (int i=0;i<otp.length();i++) 
	    	{
	    		reEnterPin.get(i).sendKeys(String.valueOf(otp.charAt(i)));
	            
	        }
    	}
    
    public void clickVerifyButton() 
    {
        click(verifyButton);
    	
    }
    public void enterPassword()
    {

    	if(isEnterSecurityPinTextPresent())
    	{
    		enterOTP();	
    	}
    	else if(isOTPVerificationTextPresent())
    			{
    				
    				enterOTP();	
    				
    				clickVerifyButton();
    				
    				if(isVerifyPINTextPresent())
    				{
    					enterPin();	
    					
    					reEnterPin();	
    					
    				}
    			}
    		
    }
    	
    public void clickProceedButton()
    {
        click(proceedButton);
    }

    public void skipPersonalisationQuestionsIfDisplayed()
    {
       try 
       { 
    	   if (isElementPresent(personalisationQuestionsText)) 
    	   {
	           for(int i=0;i<2;i++)
	           {
	        	   waitForElementToBeClickable(skipButton);
	        	  
	        	   click(skipButton);
	        	  
	        	   waitForPageToStabilize();
	           }
         
    	   }
       }
       catch (Exception e) 
       {
           log.warn("Skip button not available, continuing...");
       }
    
    }
    public void waitForPageToStabilize()
    {
        try
        {
            Thread.sleep(500); 
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
    public boolean isDashBoardPageDisplayed()
    {
      
       return isElementPresent(logoutInitiationButton);
    	
    }
    public boolean isExpectedURLDisplayed() 
    {

        return getCurrentURL().contains("/dashboard");
    }

	public boolean isBuyDigiGoldPageTextDisplayed() 
	{
        return isElementPresent(BuyDigiGoldPageText);
    }
    

    public void clickOnDigitalGoldButton()
    {
        click(digitalGoldButton);
    }

    public void clickOnDigitalSilverButton()
    {
        jsClick(digitalSilverButton);
    }

    public void clickOnLogoutButton() 
    {
    	log.info("Clicking on user button : " + userButton);
        click(userButton);
        log.info("Clicking on log out button : " + logoutButton);
    	click(logoutButton);
    }
    public void clickOnconfirmLogoutButton() 
    {
    	log.info("Clicking on comfirm button : " + confirmlogoutButton);
    	click(confirmlogoutButton);
    }
}


