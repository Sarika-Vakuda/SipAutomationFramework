package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import factory.manager.DriverManager;

public class DigitalSilverPage extends BasePage {

    @FindBy(xpath ="//h1[text()='Digital Silver']")
    private WebElement digitalSilverPageText;

    private By createSIPButton = By.xpath("//button[.//span[text()='Create SIP']]");

    public DigitalSilverPage(WebDriver driver) {
        super(driver);
        
        PageFactory.initElements(driver, this);
    }

    public String getDigitalSilverPageText() {
        return digitalSilverPageText.getText();
    }

    public String getDigitalSilverPageURL() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public void clickOnCreateSIPButton() {
        
        safeClick(createSIPButton);
    }
}
