@PANNegativeScenarios

Feature: PAN Verification Negative Scenarios

 Scenario Outline: Verify PAN verification fails for invalid inputs
    Given User launches the Augmont application
    And Clicks on the login button
    When User login with username as "<user>"
    And User clicks on Submit
   	And  User enters password
   	And User clicks on ProceedButton
   	Then User should be logged in successfully
	When User clicks on Create SIP button on Buy Digital Silver page
	Then User should be redirected to Digi KYC page
    Given User redirected to the PAN verification page
    When User enters "<user>" PAN number with other details as DOB "<month>" "<year>" "<date>" and location as "<state>" "<city>"
    Then PAN verification assertion should pass

    Examples:
        |   user   |    state     |    city      | month  | year | date  |
        | newUser2 |  Maharashtra |   Mumbai     |  Jan   | 2030 |  15   | # Future DOB
     #  | newUser1 |   Karnataka  |   Chennai    |  Mar   | 1990 |  10   | # Invalid city
     #  | newUser2 |     Hubli    |  Bangalore   |  Dec   | 1985 |  25   | # Invalid state
     #  | newUser1 |   Gujarat    |  Ahmedabad   |  Feb   | 2000 |  30   | # Invalid date (Feb 30)
     #  | newUser2 |     Delhi    |    Delhi     |  Apr   | 1995 |  15   | # Invalid PAN format
     #  |   user3  |    Kerala    |    Kannur    |  Jul   | 1988 |  20   | # Empty PAN (simulate missing input)
     #  | newUser1 |  TamilNadu   |   Chennai    |  Aug   | 1999 |  10   | # Invalid email format
     #  |   user2  |  Maharashtra |    Pune      |  May   | 1992 |  5    | # Duplicate PAN
     #  | newUser1 |   Rajasthan  |   Jaipur     |  Sep   | 2026 |  1    | # Future DOB
     #  | newUser2 |     Bihar    |   Patna      |  Nov   | 1990 |  31   | # Invalid date (Nov 31)