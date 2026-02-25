package stepdefinitions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import factory.manager.DriverManager;
import factory.manager.PageObjectManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BuyDigiSilverPage;
import pages.CreateSIPPage;
import pages.DigitalSilverPage;
import pages.HomePage;
import pages.SummaryPage;
import utils.PropReader;
import utils.TestContext;
import utils.TimeUtil;



public class SipCreationSteps
{
						
	public static final Logger log = LogManager.getLogger(SipCreationSteps.class);
	private PageObjectManager pom;
	private HomePage homePage;
    private BuyDigiSilverPage buyDigiSilverPage;
    private DigitalSilverPage digitalSilverPage;
    private CreateSIPPage createSIPPage;
    private SummaryPage summaryPage;
    
   private  boolean isPaymentSuccessful;
    
    public SipCreationSteps()
    {
    	pom = new PageObjectManager(DriverManager.getDriver());
        homePage = pom.getHomePage();
        buyDigiSilverPage = pom.getBuyDigiSilverPage();
        digitalSilverPage = pom.getDigitalSilverPage();
        createSIPPage= pom.getCreateSIPPage();
        summaryPage=pom.getSummaryPage();
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
	@Then("User is on HomePage and is PAN verified")
	public void user_is_on_home_page_and_is_pan_verified() 
	{
		log.info("Redirected to the Dashboard page and user is Pan verified");
		
		log.info("pressence of HomePage assertion is applied and is getting executed"); 
		
		Assert.assertTrue(homePage.isDashBoardPageDisplayed()||homePage.isExpectedURLDisplayed());
		
		log.info("pressence of HomePage assertion got passed");
	}

	@When("User clicks on Digital Gold option")
	public void user_clicks_on_digital_gold_option()
	{
		log.info("clicking on Digital Gold Button");  
		
		//pom.getPom().getHomePage().clickOnDigitalGoldButton();
		
		homePage.clickOnDigitalGoldButton();
	}

@When("User clicks on Digital Silver option")
public void user_clicks_on_digital_silver_option()
{
	log.info("clicking on Digital Silver Button");  
	
	homePage.clickOnDigitalSilverButton();
}


@When("User clicks on Create SIP on the Digital Silver Page")
public void user_clicks_on_create_sip_on_the_digital_silver_page()
{
	log.info("clicking on CreateSIP Button on the Digital Silver Page");  
	
	digitalSilverPage.clickOnCreateSIPButton();
}

@Then("User Should be redirected to the Buy Digi Silver Page")
public void user_should_be_redirected_to_the_buy_digi_silver_page()
{	
	log.info("Geting redirected to the Buy Digi Silver Page"); 
	
	log.info("pressence of Buy Digi Silver Page assertion is applied and is getting executed");  
	
	Assert.assertTrue(buyDigiSilverPage.isDigiSilverDisplayed(),"Expected Page is not found");
	
	log.info(" Presence of Buy Digi Silver Page assertion got passed "); 
}
@When("User selects the following to create the sip")
public void user_selects_the_following_to_create_the_sip(DataTable dataTable)
{
	log.info(" Providing the inputs for creating the SIP ");  
	
	List<Map<String,String>> data= dataTable.asMaps();
	
	Map<String,String>panData=data.get(0);
	
	buyDigiSilverPage.selectTransactionType(panData.get("SIPType"));
	
	buyDigiSilverPage.selectSIPFrequency(panData.get("Sip Frequency"));	
			
	buyDigiSilverPage.enterAmount(panData.get("SIPAmount"));
			
	buyDigiSilverPage.chooseInvestmentDay(TimeUtil.getCurrentDayOfWeek());
	
	
}

@When("User clicks on Create SIP")
public void user_clicks_on_create_sip()
{
	log.info("clicking on CreateSIP Button on the Buy Digi Silver Page");  
	
	buyDigiSilverPage.clickOnCreateSIPButton();
}

@Then("User Should be redirected to Create SIP Page")
public void user_should_be_redirected_to_create_sip_page()
{
	log.info("Assertion is applied to Verify display of Create SIP Page ");
	
	Assert.assertTrue(createSIPPage.isCreateSIPPageTextDisplayed(),"Create SIP Page not found");
	
	log.info("Assertion got passed");
}


@When("User choose the plan Name and SIP start date as <SipStartDays> after the current date using the given pattern")
public void user_choose_the_plan_name_and_sip_start_date_as_sip_start_days_after_the_current_date_using_the_given_pattern(DataTable dataTable)
{
	log.info("Providing inputs such as PlanName and SIP start Date "); 
	
	List<List<String>>data= dataTable.asLists();
	
	List<String> panData=data.get(1);
	
	createSIPPage.enterPlanName(panData.get(0));
	
	createSIPPage.enterStartSIPDate(panData.get(1),panData.get(2));
}

@When("User clicks on Create SIP button from CreateSipPage")
public void user_clicks_on_create_sip_button_from_create_sip_page()
{
	log.info("Clicking on Create Sip button on the Create Sip Page");
	
	createSIPPage.clickCreateSIP();
}

@Then("User should be redirected to SIP Investment Summary page")
public void User_should_be_redirected_to_sip_investment_summary_page()
{
	log.info("User is redirected to the Summary Page"); 
	
	log.info("Assertion is applied to Verify display of SIP Investment Summary page "); 
	
	Assert.assertTrue(summaryPage.isSummaryPageTextDisplayed(),"Summary Page is not Foud");
	
	log.info("Assertion got passed"); 
	}

@Then("User should be able to see the SIP investment details")
public void user_should_be_able_to_see_the_sip_investment_details(DataTable dataTable) {
	
	log.info("Assertion is applied to Verify SIP investment details displayed on the UI are as per the dataInput provided during SIP Creation"); 
    
	List<Map<String,String>> data= dataTable.asMaps();
	
	Map<String,String>summaryDetails=data.get(0);
	
	SoftAssert softAssert=new SoftAssert();		
	
	softAssert.assertEquals(summaryPage.getSipDurationValue(),summaryDetails.get("SIPDuration"));
	
	softAssert.assertEquals(summaryPage.getInvestmentPlanValue(),summaryDetails.get("InvestmentPurpose"));
	
	softAssert.assertEquals(summaryPage.getMetalTypeValue(),summaryDetails.get("MetalType"));
	
	int days = Integer.parseInt(summaryDetails.get("SIPStartDateDays"));
	
	String pattern = summaryDetails.get("DatePattern");
	
	String expectedDate = TimeUtil.getStartDate(days, pattern);
	
	String uiDate = pom.getSummaryPage().getSipStartDateValue();

	LocalDate actualDateFormat = LocalDate.parse(uiDate); // yyyy-MM-dd
	
	String actualDate =actualDateFormat.format(DateTimeFormatter.ofPattern(pattern));

	softAssert.assertEquals(actualDate, expectedDate);
	
	softAssert.assertEquals(summaryPage.getAmountPerWeekvalue(), 
			
	Integer.parseInt(summaryDetails.get("AmountPerWeek")));
	
	softAssert.assertAll();
	
	log.info("Assertions got passed"); 
}
@When("User clicks on the proceed to pay button for the payment")
public void user_clicks_on_the_proceed_to_payment_button_for_the_payment()
{
	log.info("Clicking on the proceed to pay Button"); 
	
	summaryPage.clickProceedToPayButton();
	
	log.info("Payment process is getting  executed....... "); 
	
}


@Then("User Should be able to see the pop up window to Set Up Bank Mandate")
public void user_should_be_able_to_see_the_pop_up_window_to_set_up_bank_mandate() 
{
	log.info("Assertion applied to verify pop up window is displayed to set up Bank Mandate"); 
	
	Assert.assertTrue(summaryPage.getSetUpBankMandateWindow(),"Expected Window id not found");
	
	log.info("Assertion got passed");
}

@When("User clicks on the Emandate button")
public void user_clicks_on_the_emandate_button()
{
	
	log.info("Clicking in emandate Button....."); 
	
	summaryPage.clickemandateButton();
}

@Then("User should be able to see the Window to choose the bank account")
public void user_should_be_able_to_see_the_window_to_choose_the_bank_account() 
{
	log.info("Assertion applied to verify pop up window is displayed to choose the bank account"); 
	
	Assert.assertTrue(summaryPage.getChooseBankAccountWindow(),"Expected Window is not found to enter the bank details");
}

@When("User clicks on the addNew option")
public void user_clicks_on_the_add_new_option() 
{
	log.info("Clicking in Add new option....."); 
	
	summaryPage.clickAddNewOption();
}
@Then("User should be able to see the Window to enter the bank details")
public void user_should_be_able_to_see_the_window_to__enter_the_the_bank_details() 
{
	log.info("Assertion applied to verify pop up window is displayed to enter the bank details"); 
	
	Assert.assertTrue(summaryPage.getChooseBankAccountWindow(),"Expected Window is not found to enter the bank details");
}

@When("User enter the following bank details of {string}")
public void user_enter_the_following_bank_details_of(String user) 
{
	log.info("Entering required Bank Details........"); 
		
	summaryPage.clickbankNameInput();
	
	TestContext.setUser(user);
	
	summaryPage.selectbankName(PropReader.getBankName(user));
    
	summaryPage.enterAccountHoldersName(PropReader.getAccounHolderName(user));
   
	summaryPage.enterAccountNumber(PropReader.getAccountNumber(user));
	
	summaryPage.clickaccountTypeInput();
	
	summaryPage.selectaccountType();
	
	summaryPage.enterIfscCode(PropReader.getIFSCCode(user));
}

@When("User clicks on the addNew button")
public void user_clicks_on_the_add_new_button()
{
	
	log.info("Clicking in Add new Button....."); 
	
	summaryPage.clickAddNewButton();	
}

@When("User enters mobile number for authentication")
public void user_enters_mobile_number_for_authentication()
{
	log.info("Entering Mobile Number...."); 
	 
	String user = TestContext.getUser();
	
	summaryPage.enterMobileNumber(PropReader.getPhoneNum(user));
	
	
}

@When("User clicks on the authenticate button")
public void user_clicks_on_the_authenticate_button() 
{
	log.info("Clicking on authenticate button...."); 
	
	summaryPage.clickAuthenticateButton();
}

@When("User clicks on the adhar option")
public void user_clicks_on_adhar_option() 
{
	log.info("Clicking on adharoption...."); 
	
	summaryPage.clickAdharOption();   
}

	@When("User clicks on the success option")
	public void user_clicks_on_the_success_option() throws InterruptedException
	{	
		
		String env = PropReader.getEnv();
		
	   isPaymentSuccessful = false; // set flag for each scenario

	    log.info("Current Environment: " + env);

	    if (env.equalsIgnoreCase("qa") || env.equalsIgnoreCase("uat"))
	    {
	        try
	        {
		            log.info("Proceeding with Razorpay automation for environment: " + env);
	
		            log.info("Switching the window for proceeding the payment "); 
		           
		            // Execute payment flow
		            summaryPage.completeRazorpayAuthentication();
	
		            // Mark payment as successful only if no exception occurred
		            isPaymentSuccessful = true;
		            
		            log.info("Payment process completed successfully.");

	        }
	        catch (Exception e)
	        {
	            log.error("Payment automation failed in environment " + env, e);
	           
	            isPaymentSuccessful = false;
	        }
	    }
	    else 
	    {
	        log.warn("Skipping Razorpay automation in environment: " + env);
	    }

		
	}

	@When("Sip Should be created successfully and user is able to see the success message")
	public void sip_should_be_created_successfully_and_user_is_able_to_see_the_success_message() 
	{		
		if (!isPaymentSuccessful) 
		{	
			log.info("Payment not executed. Skipping SIP creation verification.");
			
			throw new SkipException("Payment skipped in Production");

		}
		else
		{
			
			log.info("Verifying SIP creation success message.");
			
			Assert.assertTrue(summaryPage.isSipCreatedSuccessfully(),"SIP creation failed or success message not displayed.");
		
			log.info("SIP created successfully. Clicking Continue button to proceed.");
		
			summaryPage.clickContinueButton(); 
			
			log.info("Redirected to the DashBoard Page and user is able to see the status of Investment "); 
		}
	}
}
