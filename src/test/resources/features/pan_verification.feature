@PAN
Feature: PAN Verification for New User
	
	
Scenario Outline: Login with valid OTP and Verify PAN for a new user 
    Given User launches the Augmont application
    And Clicks on the login button
    When User login with username as "<user>"
    And User clicks on Submit
   	And  User enters password
   	And User clicks on ProceedButton
   	Then User should be logged in successfully
	When User clicks on Create SIP button on Buy Digital Silver page
	Then User should be redirected to Digi KYC page
	When User enters PAN details for "<user>" with DOB "<month>" "<year>" "<date>" and location "<state>" "<city>"
	And User clicks on Verify PAN button to verify and proceed further
	And User clicks on Proceed PAN Verification button
	Then PAN verification Check should be successful
 	
	Examples:
	| user   |    state     |       city    |   month  |   year  |   date  |
	|newUser1|  Karnataka   |   Davanagere  |    Dec   |   1988  |    18   |
	|newUser2|  Karnataka   |     Udupi     |    Sep   |   1983  |    19   |
 	
   	