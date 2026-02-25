@CreateSIP
Feature: SIP Creation for PAN verified User
   
Scenario Outline: To verify  SIP creation for PAN verified Users and Verify SIP Investment Summary details 
    Given User launches the Augmont application
    And Clicks on the login button
    When User login with username as "<user>"     
    And User clicks on Submit
   	And User enters OTP as "<password>"
   	And User clicks on ProceedButton
   	Then User should be logged in successfully
    And User is on HomePage and is PAN verified 
    When User clicks on Digital Gold option 
    And User clicks on Digital Silver option 
    And User clicks on Create SIP on the Digital Silver Page
    Then User Should be redirected to the Buy Digi Silver Page
    When User selects the following to create the sip
			|     SIPType    |  Sip Frequency   |	 SIPAmount |     
			| SIP Recommended |      Weekly     |	   5000	   |   
   
    And User clicks on Create SIP
    Then User Should be redirected to Create SIP Page
    When User choose the plan Name and SIP start date as <SipStartDays> after the current date using the given pattern
   	|   PlanName   | SipStartDays |    Pattern    |  
	| Weekly Plan  |       5       |   dd/MM/yyyy |    
    
    And User clicks on Create SIP button from CreateSipPage
    Then User should be redirected to SIP Investment Summary page 
	
   And User should be able to see the SIP investment details
   	 |  SIPDuration  | InvestmentPurpose |   MetalType |  SIPStartDateDays |    DatePattern    | AmountPerWeek |
   	 |    Weekly     | Weekly Plan       |    Silver   |  		5          |     dd/MM/yyyy    |      5000     |  
   	When User clicks on the proceed to pay button for the payment 
   	Then User Should be able to see the pop up window to Set Up Bank Mandate
   	When User clicks on the Emandate button
   	Then User should be able to see the Window to choose the bank account
   	When User clicks on the addNew option
    Then User should be able to see the Window to enter the bank details
    When User enter the following bank details of "<user>"
    And User clicks on the addNew button
    And User enters mobile number for authentication
    And User clicks on the authenticate button
    And User clicks on the adhar option  
    And User clicks on the authenticate button
    And User clicks on the success option
    And Sip Should be created successfully and user is able to see the success message
    
Examples:
|user   | password |  
|user1  |   prod   | 
|user2  |   prod   | 
    
   	 