package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BuyDigiSilverPage extends BasePage {

   

	@FindBy(xpath = "//span[contains(text(),'Digital Gold ')]")
    private WebElement digitalGoldButton;

    @FindBy(xpath = "(//div//a[contains(text(),'Digital Silver')])[2]")
    private WebElement digitalSilverButton;

    @FindBy(xpath = "//p-selectbutton[@id='transactionType']//div[@data-pc-section='button']")
    private List<WebElement> transactionTypeButtons;

    private By createSIPButton = By.xpath("//button[.//span[text()='Create SIP']]");

    @FindBy(xpath = "//span[text()='Buy Digi Silver']")
    private WebElement buyDigiSilverPageText;

    @FindBy(xpath = "//p-selectbutton[@id='frequency']//div[@role='radio']")
    private List<WebElement> sipFrequencyOptions;

    @FindBy(xpath = "//parent::div//input[@placeholder='Enter an Amount']")
    private WebElement amountInput;

    @FindBy(xpath = "(//div//span[@aria-haspopup='listbox'])[2]")
    private WebElement investmentDayDropdown;

    @FindBy(xpath = "//p-dropdownitem//li//span[@class='ng-star-inserted']")
    private List<WebElement> investmentDayOptions;

    public BuyDigiSilverPage(WebDriver driver ) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickOnDigitalGoldButton() {
        click(digitalGoldButton);
    }

    public void clickOnDigitalSilverButton() {
        clickOnVisibility(digitalSilverButton);
    }

    public void clickOnCreateSIPButton() 
    {
        
    	 safeClick(createSIPButton);
    }

   

    public void selectTransactionType(String type) {
        for (WebElement btn : transactionTypeButtons)
        {
            if (btn.getText().trim().equalsIgnoreCase(type))
            	{
            		jsClick(btn);
            		break;
            	}
        }
    }

    public void selectSIPFrequency(String frequency)
    {
        for (WebElement opt : sipFrequencyOptions)
        {
            if (opt.getText().trim().equalsIgnoreCase(frequency)) 
            {
                jsClick(opt);
                break;
            }
        }
    }

    public void enterAmount(String amount) {
    	
    	//scrollToElement(amountInput);
    	jsClick(amountInput);
    	amountInput.sendKeys(Keys.CONTROL+"a");
    	amountInput.sendKeys(Keys.DELETE);
    	clearAndSendKeys(amountInput,amount);
       
        
    }

    

	public void chooseInvestmentDay(String day) 
	{	    	
		
		//scrollToElement(investmentDayDropdown);
       
		jsClick(investmentDayDropdown);
        
        for (WebElement opt : investmentDayOptions) 
        {
            if (opt.getText().trim().equalsIgnoreCase(day)) 
            {
            	
            	click(opt);
               
            	break;
            }
        }
    }

	public boolean isDigiSilverDisplayed() 
	{
		return waitForElementVisibility(buyDigiSilverPageText).isDisplayed();
	}
}
