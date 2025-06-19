package PomUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPage {
	// Declare

		@FindBy(linkText = "Organizations")
		private WebElement header;

		@FindBy(xpath = "//img[@title='Create Organization...']")
		private WebElement orgplusicon;

		// Initialize

		public OrganizationPomPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// Utilization

		public String getHeader() {
			return header.getText();
		}

		public void getOrgplusicon() {
			orgplusicon.click();
		}
		
	}
	
	

