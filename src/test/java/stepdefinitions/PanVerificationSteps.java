package stepdefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import factory.manager.DriverManager;
import factory.manager.PageObjectManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BuyDigiSilverPage;
import pages.DigitalSilverPage;
import pages.HomePage;
import pages.KYCPage;
import utils.PropReader;

public class PanVerificationSteps
{	public static final Logger log = LogManager.getLogger(PanVerificationSteps.class);
	private PageObjectManager pom;
    private HomePage homePage;
    private BuyDigiSilverPage buyDigiSilverPage;
    private DigitalSilverPage digitalSilverPage;
    private KYCPage kycPage;

    public PanVerificationSteps()
    {
    	pom = new PageObjectManager(DriverManager.getDriver());
        homePage = pom.getHomePage();
        buyDigiSilverPage = pom.getBuyDigiSilverPage();
        digitalSilverPage = pom.getDigitalSilverPage();
        kycPage = pom.getKYCPage();
    }
	

	//  good for parallel execution and to avoid flaky tests. Instead of creating the pages inside the constructor creae the pom object and use it when ever need (lazy initialization)
    /*
     private PageObjectManager pom;

		private PageObjectManager getPom()
		 {
		    if (pom == null) 
		    {
		        pom = new PageObjectManager(DriverManager.getDriver());
		    }
		    return pom;
		}
*/
    
    
    
    @When("User clicks on Create SIP button on Buy Digital Silver page")
    public void user_clicks_on_create_sip_on_buy_silver_page() {
    	
    	 log.info("Cliclikng on Digital Gold Button.........");
    	
    	// pom.getPom().getHomePage().clickOnDigitalGoldButton();
    	 
    	 homePage.clickOnDigitalGoldButton();
    	 
    	 log.info("Cliclikng on Digital Silver Button........");
    	 
    	 homePage.clickOnDigitalSilverButton();
    	
    	 log.info("Clicking on the createSIP button form Digital Silver Page....");
    	 
    	 digitalSilverPage.clickOnCreateSIPButton();
    	 
    	 log.info("Clicking on the createSIP button form Buy Digital Silver Page.....");
    	 
    	 buyDigiSilverPage.clickOnCreateSIPButton();
    }

    @Then("User should be redirected to Digi KYC page")
    public void user_should_be_redirected_to_digi_kyc_page()
    {
    	log.info("Assertions applied to verify  presence of KycPage....");
    	
    	Assert.assertTrue(kycPage.isKycPageDisplayed(),"Digi KYC page is not displayed");
    	
    	log.info("Assertionsgot passsed....");
    }

    // -------------------- PAN DETAILS --------------------
 

    @When("User enters PAN details for {string} with DOB {string} {string} {string} and location {string} {string}")
	 public void user_enters_pan_details_for_with_DOB_and_location (String user, String month, String year, String date, String state, String city) 
	  {
	    
	    	log.info("Entering required data inputs of the new user for pan Verification....");
	        	
	       
	    	kycPage.enterPanNumber(PropReader.getPanNumber(user));
	    	
	    	kycPage.selectDOB(month, year, date);
	    	
	    	kycPage.selectState(state);
	    	
	    	kycPage.selectCity(city);
	    	
	    	 kycPage.enterEmail(PropReader.getEmail(user));
	      
	        //Code for fetching the data from the Data Table if Data table is used in the feature file
	        
	      /*List<Map<String, String>> data = dataTable.asMaps();
	        Map<String, String> panData = data.get(0);
	
	        kycPage.enterPanNumber(PropReader.getpannumber("user2.pannumber"));
	        kycPage.selectDOB(
	            panData.get("month"),
	            panData.get("year"),
	            panData.get("date")
	        );
	        kycPage.selectState(panData.get("state"));
	        kycPage.selectCity(panData.get("city"));
	        kycPage.enterEmail(PropReader.getemail("email"));
	        */
    }

    @When("User clicks on Verify PAN button to verify and proceed further")
    public void user_clicks_on_verify_pan_button_to_proceed_further()
    {
    	
    	log.info("After entering the details clicking on clickVerifyPan button......");
    	
    	kycPage.clickVerifyPan();
        
    }
    @When("User clicks on Proceed PAN Verification button")
    public void user_clicks_on_proceed_pan_verification_button() 
    {
    	log.info("Clicking on ProceedForPanVerification button......");
    	
    	kycPage.clickProceedForPanVerification();
    }

    @Then("PAN verification Check should be successful")
    public void pan_verification_check_should_be_successful()
    {
    	
    	log.info("getting message :" +kycPage.getDialogBoxTextMessage());
    	
    	log.info("Assertions applied to verify  presence of Message In Dialog Box....");
        
    	Assert.assertTrue(kycPage.isMessageInDialogBoxDisplayed(),"Pan Verification Failed.....");
    	
    	log.info("Assertion got passsed....");
    	
    	log.info("Clicking on cancel button......");
    	
    	kycPage.clickCancelButton();
    	
    	log.info("Dash board Page is displayed.....");
   
    }
}

    


