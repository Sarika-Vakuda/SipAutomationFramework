package pages;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateSIPPage extends BasePage 
{

   
	@FindBy(id = "planName")
    private WebElement planNameInput;

    @FindBy(xpath ="//td//span//td[contains(@class,'ng-star-inserted')]")
    private List<WebElement> dateOptions;
    @FindBy(xpath ="//input[@placeholder='DD-MM-YYYY']")
    private WebElement startSIPDateInput;
  
    @FindBy(xpath ="//button//span[text()='Clear']")
    private WebElement startDateInputClearButton;
    @FindBy(xpath = "//button[contains(@aria-label,'Previous Month')]")
    private WebElement previousMonthButton;

    @FindBy(xpath = "//button//span[text()='Create SIP']")
    private WebElement createSIPButton;
   
    @FindBy(xpath = "//h2[text()='Create SIP']")
    private WebElement createSIPText;
  
    @FindBy(xpath = "//button[@data-pc-name='button']//span[text()='Cancel']")
    private WebElement cancelButton;

    public CreateSIPPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterPlanName(String planName) {
        clearAndSendKeys(planNameInput, planName);
    }

    public void enterStartSIPDate(String sipStartDate, String pattern)
    {
    	
		
		click(startSIPDateInput);
		
		// Calculate the future date in the given pattern format
		
        int startDateDays = Integer.parseInt(sipStartDate);
        
		LocalDate futureDate = LocalDate.now().plusDays(startDateDays);
		                                                         
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);    //"dd/MM/yyyy"
		
		String formattedSIPStartDate = futureDate.format(formatter);
		
		
		click(startDateInputClearButton);
	            	
	    clearAndSendKeys(startSIPDateInput, formattedSIPStartDate); // Clear any existing value and enter the date
      
    			
    }

    public void clickCreateSIP()
    {
        jsClick(createSIPButton);
       
    }

    public void clickCancel()
    {
        click(cancelButton);
    }

    public boolean isCreateSIPPageTextDisplayed() {
       
    	
        return waitForElementVisibility(createSIPText).isDisplayed();
    }
}
