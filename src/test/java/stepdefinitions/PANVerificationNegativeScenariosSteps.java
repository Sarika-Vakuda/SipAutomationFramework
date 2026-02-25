
	package stepdefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import factory.manager.DriverManager;
import factory.manager.PageObjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.KYCPage;
import utils.PropReader;
import utils.LogUtil;

	public class PANVerificationNegativeScenariosSteps
	{
		PageObjectManager pom;
		KYCPage kycPage;
		private static final Logger log = LogManager.getLogger(PANVerificationNegativeScenariosSteps.class);
		
		public PANVerificationNegativeScenariosSteps()
		{
			 pom = new PageObjectManager(DriverManager.getDriver());
			 kycPage=pom.getKYCPage();
		}

	    @Given("User redirected to the PAN verification page")
	    public void user_redirected_to_pan_page()
	    {
	       
	    	log.info("Opening PAN verification page");
	       
	    } 

		 @When("User enters {string} PAN number with other details as DOB {string} {string} {string} and location as {string} {string}")
		 public void user_enters_pan_number_with_other_details_as_dob_and_location_as (String user, String month, String year, String date, String state, String city)
		   {
	       
		    	log.info("Entering negative PAN details for user: " + user);
	
		    	
		        // To handle empty PAN simulation
		        String panNumber = PropReader.getPanNumber(user);
		       
		        log.info("Entering PAN Number for user: " + user);
		        
		        if (panNumber == null || panNumber.isEmpty()) 
		        {
		            panNumber = " ";
		        }
	
		        kycPage.enterPanNumber(panNumber);
		        
		        SoftAssert softAssert =new SoftAssert();
		        
		        log.info(kycPage.getPANErrorMessage());
		        
			    softAssert.assertTrue(kycPage.getPANErrorMessage().contains("Enter a valid") ||kycPage.getDOBErrorMessage().contains("Select a"),"Expected a negative validation error but  did not found: "+kycPage.getPANErrorMessage());

		        LogUtil.log.info("Entering  DOB for user: " + user);
		        
		       
		        kycPage.selectDOB(month, year, date);
		    	   
		       log.info(kycPage.getDOBErrorMessage());
		        
		       softAssert.assertTrue(kycPage.getDOBErrorMessage().contains("Enter a valid") ||kycPage.getDOBErrorMessage().contains("Select a"),"Expected a negative validation error but  did not found: "+kycPage.getDOBErrorMessage());

		      log.info("Entering  State for user: " + user);
		       
		       	//kycPage.selectState(state);
		       
		        kycPage.selectStateFromDropdown(state);
		       
		       log.info(kycPage.getStateErrorMessage());
		       
		       softAssert.assertTrue(kycPage.getStateErrorMessage().contains("Enter a valid") ||kycPage.getDOBErrorMessage().contains("Select a"),"Expected a negative validation error but  did not found: "+kycPage.getStateErrorMessage());

		       log.info("Entering city for user: " + user);
		       
		        
		    	//kycPage.selectCity(city);
		    	
		       kycPage.selectCityFromDropdown(city);
		        
		        log.info(kycPage.getCityErrorMessage());
			       
			    softAssert.assertTrue(kycPage.getCityErrorMessage().contains("Enter a valid") ||kycPage.getDOBErrorMessage().contains("Select a"),"Expected a negative validation error but  did not found: "+kycPage.getCityErrorMessage());

		        // Handle invalid email for given User 
		        String email = PropReader.getEmail(user);
		        
		        if (user.equals("newUser1")) 
		        {
		            email = "sarikagmail.com"; // simulate invalid email
		        }
		        
		        LogUtil.log.info("Entering email for user: " + user);
	
		        kycPage.enterEmail(email);
		      
		        log.info(kycPage.getemailErrorMessage());
		        
		        softAssert.assertTrue(kycPage.getemailErrorMessage().contains("Enter a valid") ||kycPage.getDOBErrorMessage().contains("Select a"),"Expected a negative validation error but  did not found: "+kycPage.getemailErrorMessage());
 
		        softAssert.assertAll();
		  
	    }

	   @Then("PAN verification assertion should pass")
	    public void pan_verification_assertion_should_pass() 
	    {
		   log.info("After entering the details clicking on VerifyPan button......");
	    	
	    	kycPage.clickVerifyPan();
	    	
		   Assert.assertTrue(kycPage.areAnyValidationErrorsDisplayed(),"Expected validation error messages, but none were displayed");
	    
		   log.info("Assertion got passsed....");
	    	
	    	log.info("Clicking on cancel button......");
	    	
	    	kycPage.clickCancelButton();
	    	
	    	log.info("Dash board Page is displayed.....");
	   
	    }
	}
