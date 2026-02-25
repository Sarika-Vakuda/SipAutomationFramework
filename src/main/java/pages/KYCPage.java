package pages;


import java.util.List;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




public class KYCPage extends BasePage
{

    @FindBy(xpath = "//div[text()=' DIGI KYC ']//ancestor::section")
    private WebElement kycPageHeader;

    @FindBy(css = "#panCardNumber")
    private WebElement panNumberInput;

    @FindBy(xpath ="//small[text()='Enter a valid PAN Number']")
    private WebElement panNumberErorMessage;
 
    @FindBy(id = "dateOfBirth")
    private WebElement dobInput;
   
    @FindBy(xpath = "//div[contains(@class,'p-datepicker-header')]")
    private WebElement yearHeader;

    @FindBy(xpath = "//span[contains(@class,'p-monthpicker-month')]")
    private List<WebElement> monthOptions;

    @FindBy(xpath = "//button[@aria-label='Previous Year']")
    private WebElement previousYearButton;

    @FindBy(xpath = "//button//chevronrighticon[contains(@class,'p-')]")
    private WebElement nextYearButton;
    
    @FindBy(xpath = "//tbody//td[@class='ng-star-inserted']")
    private List<WebElement> dateOptions;
    
    @FindBy(xpath ="//small[text()='Enter a valid DOB']")
    private WebElement dobErrorMessage;
    
    @FindBy(xpath = "(//span[@role='combobox'])[1]")
    private WebElement stateDropdown;

  
    @FindBy(xpath = "//p-dropdownitem//li//span[@class='ng-star-inserted']")
    private List<WebElement> stateOptions;
  
    @FindBy(xpath ="//small[text()='Select a State']")
    private WebElement stateErrorMessage;
    

    @FindBy(xpath = "(//span[@role='combobox'])[2]")
    private WebElement cityDropdown;
   
    
    @FindBy(xpath = "//p-dropdownitem//li//span[@class='ng-star-inserted']")
    private List<WebElement> cityOptions;
    
    @FindBy(xpath ="//small[text()='Select a City/District']")
    private WebElement cityErrorMessage;


    @FindBy(id = "email")
    private WebElement emailInput;
   
    @FindBy(xpath ="//span[text()='Enter a valid Email']")
    private WebElement emailErrorMessage;
    

    @FindBy(xpath = "//span[text()='Verify']")
    private WebElement verifyButton;

    @FindBy(xpath = "//span[text()='Proceed for PAN Verification']")
    private WebElement proceedForPanVerificationButton;
    
    @FindBy(xpath = "//span[contains(text(),'Verification Error!')]")
    private WebElement errorMessageDilogBox;
    
    @FindBy(xpath = "//div[contains(text(),'PAN Card already exists!')]")
    private WebElement panCardExistsMessage;
  
    @FindBy(xpath = "//span[contains(text(),'All Set! Verified!')]")
    private WebElement panVerificationSuccessMessage;

    @FindBy(xpath = "(//button//span[contains(text(),'Proceed')])[2]")
    private WebElement proceedButton;
    @FindBy(xpath = "//button//span[text()='Cancel']")
    private WebElement cancelButton;
  
    @FindBy(xpath = "//div[contains(text(),' DIGI KYC')] ")
    private WebElement kycPageText;
  
    @FindBy(xpath = "//span[contains(text(),'Experience Smooth KYC ')]")
    private WebElement panVerificationDialogBoxText;
    
  
    @FindBy(xpath = "//div[contains(text(),' DIGI KYC')] ")
    private WebElement errorMessage;
    
    private static final Logger log = LogManager.getLogger(KYCPage.class);
    public KYCPage(WebDriver driver) 
    {
        super(driver);
        
        PageFactory.initElements(driver, this);
    }
    public  boolean isKycPageTextDisplayed() 
    {
        return kycPageText.isDisplayed();
    }
    public boolean isKycPageDisplayed()
    {
        return waitForElementVisibility(kycPageHeader).isDisplayed();
    }

    public void enterPanNumber(String panNumber) 
    {
    	waitForElementToBeClickable(panNumberInput);
    	clearAndSendKeys(panNumberInput, panNumber);
    }

    public void selectDOB(String month, String year, String date)
    {
       /* click(dobInput);
        click(yearHeader);
        int currentYear = Year.now().getValue();
        		
        int maxTries = 45;
       while (!yearHeader.getText().equals(year) && maxTries-- > 0)
       {
            click(previousYearButton);
       
       }*/
       
       
       click(dobInput);  // Open the date picker
       click(yearHeader);  // Open year selection (assuming clicking yearHeader opens the year list)

       int targetYear = Integer.parseInt(year);
       int maxTries = 45;

       // Loop to navigate to the correct year
       while ( maxTries-- > 0) 
       {
    	   String displayedText = yearHeader.getText(); // e.g., "January 2026"
          
    	   String numericYearStr = displayedText.replaceAll("[^0-9]", ""); // "2026"
          
    	   int displayedYear = Integer.parseInt(numericYearStr);

           if (displayedYear > targetYear)
           {
               // If displayed year is greater than target, go to previous year
               click(previousYearButton);
           } 
           else if (displayedYear < targetYear) 
           {
               // If displayed year is less than target, go to next year
        	   if (nextYearButton.isEnabled())
        	   {
                  click(nextYearButton);
               } 
        	   else 
        	   {
        		   	log.error("Target year is disabled.");
               }
        	 
           } else  
           {
        	// If displayed year equals to targetYear break the loop
               break;
           }
       }
       
       
        for (WebElement mon : monthOptions) 
        {
            if (mon.getText().equalsIgnoreCase(month))
            {
            	if (mon.isEnabled())
         	   {
            		jsClick(mon);
               } 
         	   else 
         	   {
         		  log.error("Target month is in the future and disabled.");
               }
                
                break;
            }
        }

        for (WebElement d : dateOptions) 
        {
            if (d.getText().equals(date)) 
            {
	            	if (d.isEnabled())
			      	   {
			                jsClick(d);
			      	   }
		            else 
		     	   {
		               log.error("Target day is in the future and disabled.");
		     	   }
               break;
            }
        }
    }

    public void selectState(String state)
    {
        jsClick(stateDropdown);
        
        for (WebElement st : stateOptions) 
        {
            if (st.getText().equalsIgnoreCase(state))
            {
                click(st);
                break;
            }
        }
    }
	
    public void selectStateFromDropdown(String state)
    {
    	selectFromDropdown(stateDropdown,stateOptions,state);
    }
   


	public void selectCity(String city)
	{

    int attempts = 0;
    int maxRetries = 3;

    while (attempts < maxRetries)
    {
        try
        {
	            click(cityDropdown);
	
	            for (WebElement ct : cityOptions) 
	            {
	                if (ct.getText().equalsIgnoreCase(city))
	                {
	                    click(ct);
	                    return; // success → exit method
	                }
	            }
	
	            throw new RuntimeException("City not found in dropdown: " + city);

        }
        catch (StaleElementReferenceException | ElementClickInterceptedException e) 
        {
            attempts++;

            if (attempts == maxRetries)
            {
                throw e; //  fail after max retries
            }

            // small wait before retry
            try
            {
            	Thread.sleep(1000);
            } 
            catch (InterruptedException ex)
            {
            	ex.getMessage();
            }
        }
    }
	}

	public void selectCityFromDropdown(String city)
	{
		selectFromDropdown(cityDropdown,cityOptions,city);
	}
   
    public void enterEmail(String email)
    {
    	
        clearAndSendKeys(emailInput, email);
    }

    public void clickVerifyPan() 
    {
        jsClick(verifyButton);
    }

    public void clickProceedForPanVerification()
    {
        jsClick(proceedForPanVerificationButton);
    }

    public boolean isMessageInDialogBoxDisplayed() 
    {
     
    	
    	    if (isElementPresent(errorMessageDilogBox))
    	    {
    	    	return waitForElementVisibility(errorMessageDilogBox).isDisplayed(); 
    	        
    	    }
    	    else if(isElementPresent(panVerificationSuccessMessage))
    	    {
    	        return waitForElementVisibility(panVerificationSuccessMessage).isDisplayed(); 
    	    }
    	    else
    	    {
    	    	return waitForElementVisibility(panCardExistsMessage).isDisplayed();
    	    }
    	  
    	}
    	
      
    public String getDialogBoxTextMessage()
    {
        if (isElementPresent(panVerificationSuccessMessage)) 
        {
            return waitForElementVisibility(panVerificationSuccessMessage).getText();
        } 
        else if (isElementPresent(panCardExistsMessage)) 
        {
            return waitForElementVisibility(panCardExistsMessage).getText();
        }
        else
        {
            return waitForElementVisibility(errorMessageDilogBox).getText();
        }
       
    } 

    public void clickProceed() 
    {
        click(proceedButton);
    }
    public void clickCancelButton()
    {
        click(cancelButton);
    }
    public boolean areAnyValidationErrorsDisplayed()
    {
       if(isPanErrorDisplayed()
                || isDobErrorDisplayed()
                || isStateErrorDisplayed()
                || isCityErrorDisplayed()
                || isEmailErrorDisplayed()
                || isPanVerificationErrorDisplayed())
        {
        	return true;
        }
        return false;
    }
   
    public boolean isPanErrorDisplayed()
    {
        return waitForVisibility(panNumberErorMessage);
    }

    public boolean isDobErrorDisplayed() 
    {
        return waitForVisibility(dobErrorMessage);
    }

    public boolean isStateErrorDisplayed() 
    {
        return waitForVisibility(stateErrorMessage);
    }

    public boolean isCityErrorDisplayed() 
    {
        return waitForVisibility(cityErrorMessage);
    }

    public boolean isEmailErrorDisplayed()
    {
        return waitForVisibility(emailErrorMessage);
    }

    public boolean isPanVerificationErrorDisplayed() 
    {
        return waitForVisibility(errorMessageDilogBox)
                || waitForVisibility(panCardExistsMessage);
    }
    public boolean isPanVerificationErrorMessageDisplayed()
      {
        try
         {
        	waitForElementVisibility(errorMessageDilogBox);
        	
        	waitForElementVisibility(panCardExistsMessage).isDisplayed();
           
        	return waitForElementVisibility(panCardExistsMessage).isDisplayed();
        }
        catch (NoSuchElementException e)
        {
                 
          return false;
        }
		
    }
    public String getPANErrorMessage()
    {
    	try
        {
        	
        	 waitForElementVisibility(panNumberErorMessage).isDisplayed();
        	 return panNumberErorMessage.getText();
        }
        catch (NoSuchElementException e)
        {
        	return null;
        }
	
    }
    
    public String getDOBErrorMessage()
    {
    	try
        {
        	
        	 waitForElementVisibility(dobErrorMessage).isDisplayed();
        	 return dobErrorMessage.getText();
        }
        catch (NoSuchElementException e)
        {
                 
          return  null;
        }
    }
    
    public String getStateErrorMessage()
    {
    	try
        {
        	 waitForElementVisibility(stateErrorMessage).isDisplayed();
        	 
        	 return stateErrorMessage.getText();
        }
        catch (NoSuchElementException e)
        {
                 
        	return null;
        }
    }
    
    public String getCityErrorMessage()
    {
    	try
        {
        	
        	 waitForElementVisibility(cityErrorMessage).isDisplayed();
        	
        	 return cityErrorMessage.getText();
        }
        catch (NoSuchElementException e)
        {
                 
          return null;
        }
    }
    
    
    public String getemailErrorMessage()
    {
    	try
        {
        	
        	 waitForElementVisibility(emailErrorMessage).isDisplayed();
        	 
        	 return emailErrorMessage.getText();
        }
        catch (NoSuchElementException e)
        {
                 
          return null;
        }
    }
    
    public boolean ispanVerificationDialogBoxTextPresent()
 
   {
    	return waitForElementVisibility(panVerificationDialogBoxText).isDisplayed();
   }


    
    }
