
	package locators;

	import org.openqa.selenium.By;
	
	
	/* Locator class for Razorpay payment page
	 * Maintains all UI element identifiers used in Razorpay flow
	 */
	

	public class RazorpayLocators 
	{
	    public static final By MOBILE_NUMBER_INPUT = By.xpath("//input[@id='contact']");
	    public static final By AUTHENTICATE_BUTTON = By.xpath("//button[contains(text(),'Authenticate')]");
	    public static final By AADHAR_OPTION = By.xpath("(//div//label[@class='svelte-13gz49'])[3]");
	    public static final By SUCCESS_OPTION = By.xpath("//button[contains(text(),'Success')]");
	   // public static final By RAZORPAY_IFRAME = By.xpath("//iframe[contains(@src,'razorpay')]");
	    public static final By RAZORPAY_IFRAME = By.cssSelector("iframe[src*='razorpay']");
	}

	



