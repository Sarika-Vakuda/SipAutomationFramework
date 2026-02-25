
	package pages;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;

	    
	public class LoginPage extends BasePage
	{

		private static final Logger log = LogManager.getLogger(LoginPage.class);
		
	    @FindBy(xpath = "//button[@aria-label='Close']")
	    private WebElement cancelButton;

	    @FindBy(xpath = "//a[contains(text(),'Log In / Sign Up')]")
	    private WebElement loginButton;

	  
	    public LoginPage(WebDriver driver)
	    {
	        super(driver);
	        PageFactory.initElements(driver, this);
	    }

	    public void clickOnCancelButtonIfPresent() 
	    {
	    	log.info("Clicking on cancel button : " + cancelButton);
	    	try
	    	{
	    		 if (isElementPresent(cancelButton))
	 	        {
	 	            click(cancelButton);
	 	        }
	    	}
	       catch(Exception e)
	       {
	    	   log.info("Cancel button is not present : " +e);
	    	   
	    	   return ;
	       }
	    }

	    public void clickOnLoginButton() 
	    {
	    	log.info("Clicking on login button : " + loginButton);
	        jsClick(loginButton);
	    }

	   
	    
	}

