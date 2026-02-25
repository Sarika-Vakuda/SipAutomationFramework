![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-Automation-purple)
![Cucumber](https://img.shields.io/badge/BDD-Cucumber-brightgreen)
![Build](https://img.shields.io/badge/Maven-Build-brown)
![Status](https://img.shields.io/badge/Project-Assessment-orange)

---

# Selenium-BDD-UI-Automation-Framework
 
---

 **Assessment Project – This project is created as part of an assessment to demonstrate automation skills using Selenium, Cucumber BDD, TestNG and Java.**

---

## Tech Stack

- Java 17
- Selenium WebDriver
- Cucumber BDD
- TestNG
- Extent Reports
- Allure Reports
- Log4j2
- Maven

---


## Project Overview

- This project is a **Java-based Selenium automation framework** built using **Cucumber BDD** and **Page Object Model (POM)** design 

   pattern.  

- The automation covers the full user journey, from login and PAN verification to SIP creation, payment authentication, and investment  

   summary validation and provides **detailed reporting and email notifications** for test results.  

- The framework is designed for **scalability, maintainability, and reusability**.


---


## Project Features & Highlights

### Core Features

- Home Page Verification: Ensures user is PAN verified and on the dashboard.

- Digital Silver & Gold Navigation: Automates navigation and page validations.

- SIP Creation:

     - Transaction type selection

     - SIP frequency and amount input

     - Start date calculation based on current date

- Payment Automation: Simulates Razorpay payment for QA/UAT environments.

- Bank Mandate Setup: Handles e-mandate, bank selection, and mobile authentication.

- SIP Investment Summary Validation: Verifies all details using Soft Assertions.

- Environment Awareness: Skips payment automation for production to avoid real transactions.

### Key Highlights

- Page Object Model (POM) – Clean separation of page interactions.

- Cucumber BDD – Easily Write human-readable test scenarios.

- Hooks – Setup and teardown with Cucumber hooks.

- Maven-based project – Dependency management simplified.

- Cross-browser support – Chrome, Edge, Firefox.

- Environment-specific configurations – QA, UAT, PROD.

- Email Notifications – Automated test result emails to QA team and manager.

- Logging – Configured with Log4j2 for detailed logs.

- Utilities – Logging, email, property reader, time utils, and more.

- Parallel Execution – Support for running tests concurrently.

- Reporting – Interactive test reports with screenshots on failures:

    - ExtentReports
 
    - Allure Reports

    - Screenshots for failed tests


--- 
## Project Structure


```

project-root/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── locators/    # Razor Pay locators
│   │   │   ├── manager/     # Driver and Page Object managers
│   │   │   ├── pages/       # Page Object Model classes
│   │   │   └── utils/       # Utility/helper classes
│   │   └── resources/
│   │       └── config/      # Main resource files (if any)
│   │
│   └── test/
│       ├── java/
│       │   ├── hooks/             # Cucumber hooks (Before/After)
│       │   ├── reports/           # Reporting utilities (ExtentReports)
│       │   ├── runner/            # Test runners
│       │   └── stepdefinitions/   # Step definition classes for Cucumber
│       │
│       └── resources/
│           ├── config/        # Config files (properties, xml)
│           ├── features/      # Cucumber feature files
│           └── screenshots/   # Screenshots captured during tests

│
├── allure-results/
├── logs/
├── pom.xml
└── README.md

````

---


## Prerequisites

Before running the tests, make sure you have:

- Java JDK 21 installed

- Maven installed

- Chrome, Firefox, or Edge browser

- IDE (IntelliJ IDEA, Eclipse, VSCode)

- Internet connection (for downloading Maven dependencies)

---

## Setup & Installation

- Clone the repository

	- `git clone <repository-url> cd <project-folder>`

- Install dependencies

- Maven will automatically download all dependencies defined in pom.xml.

     - `mvn clean install`

---


## Configuration Management

 -  `config.properties.example`  contains placeholders for sensitive data

 *Example properties:*
 	
	browser=chrome
	
	env=qa
	
	env.qa.url=https://qa-example.com/
	
	login.qa.password=123456
	
	user1.email=example@gmail.com

- Copy `config.properties.example` to `config.properties`

- Update URLs, browser, credentials, and users in `config.properties`  



 
---


# How to Run

 -  Clone the project from GitHub.

-  Update the `config/config.properties` file with your environment details.  
-  Open the project in **IDE (Eclipse/IntelliJ)**.
-  Run test via:
    - Cucumber: 
    - use the specific test Runner (`Runner.java`) located in `src/test/java/runner/Runner.java`  
    - You can run tests using Maven or your IDE.
    - From the command line:
    - Maven: 
    - `mvn clean test -Dcucumber.filter.tags="@CreateSIP"` 
	 - `mvn test -Dcucumber.options=RetryRunner`    
    - `mvn test -Dtest=RetryRunner`                
    - `mvn test -Dsurefire.suiteXmlFiles=testng.xml` 
    - `mvn test -Dcucumber.filter.tags="@PAN" -Dbrowser=chrome`      
    - `Run specific feature file`
    - `mvn test -Dcucumber.options=src/test/resources/features/sip_creation.feature`

-  Reports will be generated in "target" and "screenshots" in `src/test/resources/screenshots`.


---


## Reports

# ExtentReports: Check "target/ExtentReports.html"

- Reports are generated under  target/

- ExtentReports are integrated for detailed HTML reports.

# CucumberReports: Check "target/cucumber-report/cucumber.html" 

- Reports are generated under  target/cucumber-report

- Screenshots on failure are saved in src/test/resources/screenshots

# Allure Reports: Generate and open


- Allure reports can be generated from allure-results.

   - run command on cmdpromt - `allure serve allure-results`



---


# Email Notifications:

After test execution, an automated email is sent to configured users with test summary.

Configure recipients and SMTP details in the email.properties or utility class.

---


# Razorpay Automation in QA/UAT

- Razorpay payment automation is executed only in QA and UAT environments.

- In Production or other environments, payment steps are safely skipped.

- Controlled using the `env` property in `config.properties`.


---
 
# Troubleshooting

- Ensure browser drivers are compatible with your browser version.

- Check log4j2.xml for logging configuration.

- Verify that config.properties settings match your environment.

- For Maven build errors, try cleaning and rebuilding the project:
- If build fails 
     - `mvn clean install`


---

# Author

## *Sarika Vakuda*

---
