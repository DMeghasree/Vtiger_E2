package PomUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPomPage {
	
	// Declare
		@FindBy(linkText = "Contacts")
		private WebElement header;
		@FindBy(xpath = "//img[@alt='Create Contact...']")
		private WebElement contplusicon;

		// Intialize
		public ContactPomPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

		// Utilize
		public String getHeader() {
			return header.getText();
		}

		public void getContplusicon() {
			contplusicon.click();
		}
}
