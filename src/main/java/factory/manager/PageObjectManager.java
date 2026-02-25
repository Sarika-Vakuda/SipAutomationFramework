

	package factory.manager;
	import org.openqa.selenium.WebDriver;


import pages.BuyDigiSilverPage;
import pages.CreateSIPPage;
import pages.DigitalSilverPage;
import pages.HomePage;
import pages.KYCPage;
import pages.LoginPage;
import pages.SummaryPage;

/**
 * PageObjectManager is responsible for creating and managing
 * all Page Object instances in a centralized manner.
 */
	public class PageObjectManager {

		private final WebDriver driver;

	    private LoginPage loginPage;
	    private HomePage homePage;
	    private BuyDigiSilverPage buyDigiSilverPage;
	    private CreateSIPPage createSIPPage;
	    private DigitalSilverPage digitalSilverPage;
	    private KYCPage kycPage;
	    private SummaryPage summaryPage;

	    public PageObjectManager(WebDriver driver)
	    {
	        this.driver = driver;
	    }

	    public LoginPage getLoginPage() {
	        if (loginPage == null)
	        {
	        	loginPage = new LoginPage(driver);
	        }
	        return loginPage;
	    }

	    public HomePage getHomePage() {
	        if (homePage == null) 
	        {
	        	homePage = new HomePage(driver);
	        }
	        return homePage;
	    }

	 /*   public BasePage getBasePage() {
	        if (basePage == null) {
	        	
	        	basePage = new BasePage(driver);
	        }
	        return basePage;
	    }*/

	    public BuyDigiSilverPage getBuyDigiSilverPage() {
	        if (buyDigiSilverPage == null)
	        {
	        	buyDigiSilverPage = new BuyDigiSilverPage(driver);
	        }
	        return buyDigiSilverPage;
	    }

	    public CreateSIPPage getCreateSIPPage() {
	        if (createSIPPage == null) 
	        {
	        	
	        	createSIPPage = new CreateSIPPage(driver);
	        }
	        return createSIPPage;
	    }

	    public DigitalSilverPage getDigitalSilverPage() {
	        if (digitalSilverPage == null) 
	        {
	        	digitalSilverPage = new DigitalSilverPage(driver);
	        }
	        return digitalSilverPage;
	    }

	    public KYCPage getKYCPage() {
	        if (kycPage == null) 
	        	{
	        	kycPage = new KYCPage(driver);
	        	}
	        return kycPage;
	    }

	    public SummaryPage getSummaryPage() {
	        if (summaryPage == null)
	        {
	        	summaryPage = new SummaryPage(driver);
	        }
	        return summaryPage;
	    }
	}