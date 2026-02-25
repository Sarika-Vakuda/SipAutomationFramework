package pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import locators.RazorpayLocators;



public class SummaryPage extends BasePage
{

		@FindBy(xpath = "//h2[text()='Summary']")
	    private WebElement summaryPageText;
		
		@FindBy(xpath = "(//span[contains(@class,'font-gilroybold text-darkgunmetal')])[1]")
		private WebElement SipDurationValue;
		 
	    @FindBy(xpath = "//span[text()='Investment Purpose']")
	    private WebElement investmentPlan;
	    
	    @FindBy(xpath = "(//span[contains(@class,'font-gilroybold text-darkgunmetal')])[2]")
	    private WebElement investmentPlanValue;
	    
	    @FindBy(xpath = "//span[text()='Metal Type']")
	    private WebElement metalType;
	    
	    @FindBy(xpath = "(//span[contains(@class,'font-gilroybold text-darkgunmetal')])[3]")
	    private WebElement metalTypeValue;
	    
	    @FindBy(xpath = "//span[text()='SIP Start Date']")
	    private WebElement sipStartDate;
	    
	    @FindBy(xpath = "(//span[contains(@class,'font-gilroybold text-darkgunmetal')])[4]")
	    private WebElement sipStartDateValue;
	    
	    @FindBy(xpath = "//span[text()='Amount Per Week']")
	    private WebElement amountPerWeek;
	    
	    @FindBy(xpath = "(//span[contains(@class,'font-gilroybold text-darkgunmetal')])[5]")
	    private WebElement amountPerWeekvalue;
	    
	    @FindBy(xpath = "//button//span[text()='Proceed To Pay']")
	    private WebElement proceedToPayButton;
	    
	    
	    @FindBy(xpath = "//div//span[text()='Set Up Bank Mandate']")
	    private WebElement setUpBankMandateWindow;
	    
	    @FindBy(xpath = "//button//span[text()='Emandate']")
	    private WebElement emandateButton;
	    
	    
	    @FindBy(xpath = "//div//span[text()='Choose Bank Account']")
	    private WebElement ChooseBankAccountWindow;
	    
	    @FindBy(xpath = "//span[text()='Add New']")
	    private WebElement addNewOption ;
	    
	    @FindBy(xpath = "//div//span[@aria-label='--Select--']")
	    private WebElement bankNameInput;
	    
	    @FindBy(xpath = "//li//span[@class='ng-star-inserted']")
	    private List<WebElement> bankNameOptions;
	    
	    @FindBy(xpath = "//div//input[@placeholder='Account Holder’s Name']")
	    private WebElement accountHoldersNameInput;
	    
	    @FindBy(xpath = "//div//input[@placeholder='Account Number']")
	    private WebElement accountNumberInput;
	    
	    @FindBy(xpath = "//div//span[@aria-label='Account Type']")
	    private WebElement accountTypeInput;
	    
	    @FindBy(xpath = "//li[contains(@id,'pn_id')]//span[@class='ng-star-inserted']")
	    private List<WebElement> accountTypeOptions;
	   
	    @FindBy(xpath = "//div//input[@id='ifscCode']")
	    private  WebElement ifscCodeInput;
	   
	    @FindBy(xpath = "//button//span[text()='Add New']")
	    private  WebElement addNewButton;
	    
	  //div[contains(@class,'ng-tns-c4033847114-26 p-dialog-mask p-component-overlay ')]
	    
	    @FindBy(xpath="//div[@role='dialog' and contains(@class,'p-dialog')]")
	    private  WebElement successDialog;
	    
	    @FindBy(xpath="//div[@role='dialog']//*[normalize-space()='Success']")
	    private  WebElement successTitle;
	   
	    @FindBy(xpath="//div[@role='dialog']//*[contains(text(),'successfully created')]")
	    private  WebElement successMessage;
	
	    @FindBy(xpath="//button//span[text()='Continue']")
	    private  WebElement continueButton;
	
	  
	    public SummaryPage(WebDriver driver)
	    {
	        super(driver);
	        PageFactory.initElements(driver, this);
	    }

	    
	    public boolean isSummaryPageTextDisplayed() 
	    {
	    	return waitForElementVisibility(summaryPageText).isDisplayed();
	   
	    }
	    
	    public String getSipDurationValue()
	    {
	    	return waitForElementVisibility(SipDurationValue).getText().trim();
	    }
	    
	    public String getInvestmentPlanValue() 
	    {
	    	return waitForElementVisibility(investmentPlanValue).getText().trim();
	   
	    }
		
	    public String getMetalTypeValue()
	    {
	    	return waitForElementVisibility(metalTypeValue).getText().trim();
	   
	    }
	    public String getSipStartDateValue() 
	    {
	    	return waitForElementVisibility(sipStartDateValue)
	    			.getText()
	    			.trim();
	    }
	    public int getAmountPerWeekvalue()
	    {
	    	 String amount=waitForElementVisibility(amountPerWeekvalue)
	    			 .getText()
	    			 .trim()
	    			 .replaceAll("[^0-9]","");
	    	 return Integer.parseInt(amount);
	   
	    }
	    public void clickProceedToPayButton()
	    {
	    	click(proceedToPayButton);
	   
	    }
	    public boolean getSetUpBankMandateWindow()
	    {
	    	return waitForElementVisibility(setUpBankMandateWindow).isDisplayed();
	   
	    }
	    public void clickemandateButton()
	    {
	    	click(emandateButton);
	   
	    }
	    public boolean getChooseBankAccountWindow()
	    {
	    	return waitForElementVisibility(ChooseBankAccountWindow).isDisplayed();
	   
	    }
	    
	    public void clickAddNewOption()
	    {
	    	click(addNewOption);
	   
	    }
	    
	    public void clickbankNameInput()
	    {
	    	jsClick(bankNameInput);
	   
	    }
	    public void selectbankName(String bankName)
	    {	
	    	
	    	for(WebElement bankMane :bankNameOptions)
	    	{
	    		if(bankMane.getText().toLowerCase().contains(bankName))
	    		{
	    			jsClick(bankMane);
	    			
	    			break;
	   
	    		}
	    
	    	}
	
	    }
	    
	    public void clickaccountTypeInput()
	    {
	    	click(accountTypeInput);
	    }
	    
	    public void selectaccountType()
	    {	
	    	
	    	
	    	for(WebElement accountType :accountTypeOptions)
	    	{
	    		if(accountType.getText().toLowerCase().contains("current"))
	    		{
	    			click(accountType);
	    			
	    			break;
	   
	    		}
	    
	    	}
	
	    }
	    
	    public void enterAccountHoldersName(String accntHolderName)
	    {
	    	click(accountHoldersNameInput);
	    	
	    	clearAndSendKeys(accountHoldersNameInput, accntHolderName);
	    }
	    
	    public void enterAccountNumber(String accountNum )
	    {
	    	click(accountNumberInput);

	    	clearAndSendKeys(accountNumberInput,accountNum);
	    }
	    
	    public void enterIfscCode(String ifscCode )
	    {
	    	
	    	click(ifscCodeInput);
	    	
	    	clearAndSendKeys(ifscCodeInput,ifscCode);
	    }
	    public void clickAddNewButton()
	    {
	    	scrollToElement(addNewButton);
	    	click(addNewButton);
	   
	    }  
	    
	    public void enterMobileNumber(String mobileNum)
	    {
	    	switchToRazorpayFrame(RazorpayLocators.RAZORPAY_IFRAME);
	    	
	        clearAndSendKeys(RazorpayLocators.MOBILE_NUMBER_INPUT, mobileNum);
	    }

	    public void clickAuthenticateButton()
	    {
	        click(RazorpayLocators.AUTHENTICATE_BUTTON);
	    }
	    
	    public void clickAdharOption()
	    {
	        click(RazorpayLocators.AADHAR_OPTION);
	    }
	    public void clickSuccessOption()
	    {
	        click(RazorpayLocators.SUCCESS_OPTION);
	    }
	    
	    public void completeRazorpayAuthentication() 
	    {

	      	// Switching to the Parent window
		 		String parentWindow = getCurrentWindow();
		   
	      	// Switch to Razorpay child window
	      		switchToNewWindow() ;
	    
	      		
	      	//Retrying logic for flaky tests/Payments 
	      		retryingClickOnRazorPayWindow(RazorpayLocators.SUCCESS_OPTION, 3);
	      		
		     // Wait for Razorpay window to close
	      		
	      		waitForOnlyParentWindow();

	      	  
	      		// Switch back to parent window
	      		
	      	  switchBackToParent(parentWindow);
		     
	      		
		 }
	     
	     public void waitForSuccessPopup() 
	     {
	    	 waitForElementVisibility(successDialog);
	     }  
	    
	     public boolean isSuccesTitleDisplayed()
		   {
		    	
	    	 return waitForElementVisibility(successTitle).isDisplayed();
		    	
		   }
	     
		 public boolean isSipCreatedSuccessfully() 
		 {
			
			 waitForSuccessPopup();
	         
	         isSuccesTitleDisplayed();
	         
	         return waitForElementVisibility(successMessage).isDisplayed();
	         
	     }
	
	    
	    public void clickContinueButton()
	    {
	    	click(continueButton);
	    	
	    }
	    
	   
	   
}
