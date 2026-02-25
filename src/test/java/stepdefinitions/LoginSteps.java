package stepdefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import factory.manager.DriverManager;
import factory.manager.PageObjectManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import utils.PropReader;
import utils.TestContext;

public class LoginSteps {

	public static final Logger log = LogManager.getLogger(LoginSteps.class);
	private PageObjectManager pom;
    private HomePage homePage;
    private LoginPage loginPage;
	
	public LoginSteps()
	{
		
		 pom = new PageObjectManager(DriverManager.getDriver());
		 
	     homePage = pom.getHomePage();
	        
	     loginPage=pom.getLoginPage();
	        
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
	
	
	@Given("User launches the Augmont application")
	public void user_launches_the_augmont_application() 
	{
		log.info("Launching Augmont application");
		
       // DriverManager.getDriver().get(PropReader.getEnvURL("envURL"));
        
		log.info("Clicking on cancel button....");
		
		loginPage.clickOnCancelButtonIfPresent();
		
	}

	@Given("Clicks on the login button")
	public void clicks_on_the_login_button()
	{
		log.info("Clicking on login button....");
		
		//pom.getPom().getPom()..clickOnLoginButton();
		
		loginPage.clickOnLoginButton();
		
		log.info("Clicked on login button");
	}
	@When("User login with username as {string}")
	public void user_login_with_username_as(String user)
	{
		
	    TestContext.setUser(user);

	    String mobile = PropReader.getPhoneNum(user);
	    
	    homePage.enterMobileNumber(mobile);
	   
	    log.info("Entered mobile number....");
	}
	
	@When("User clicks on Submit")
	public void user_clicks_on_submit() 
	{
		log.info("Clicking on Submit button....");
		
		homePage.clickSubmitButton();
		
		log.info("Clicked on Submit button.....");
	}

	/*@When("User enters password as {string}")
	public void user_enters_otp_as(String password) 
	{
		 
		log.info("Entering the login Password....");
		 
		 TestContext.setUser(password);
		 
		 homePage.enterPassword(password);
		 
		 log.info("Entered Password.");
	}*/
	@When("User enters password")
	public void user_enters_password() 
	{
		 
		log.info("Entering the login Password....");
		 
		
		 
		 homePage.enterPassword();
	}

	@When("User clicks on ProceedButton")
	public void user_clicks_on_proceed_button() 
	{ 
		 log.info("Clicking on Proceed Button....");
		
		 homePage.clickProceedButton();
		 
		 log.info("Clicked on Proceed Button and verifying presence of Personalisation Questions and steps getting executed to skip the questions");
		 
		 homePage.skipPersonalisationQuestionsIfDisplayed();
		 
		 log.info("Verified presence of Personalisation Questions....... ");
	    
	}

	@Then("User should be logged in successfully")
	public void user_should_be_Logged_in_successfully()
		{
			log.info("User logged in successfully......");
		
			log.info("Assertions applied to verify  presence of Home page for pan registered user and Buy Digi Gold Page for new User....... ");
			
			Assert.assertTrue( homePage.isDashBoardPageDisplayed()|| homePage.isExpectedURLDisplayed()
					
			|| homePage.isBuyDigiGoldPageTextDisplayed(),"login Failed...!");

			log.info("Assertion got passed.... ");
		}


}
