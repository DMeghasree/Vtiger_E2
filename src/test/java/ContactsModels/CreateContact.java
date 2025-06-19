package ContactsModels;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import PomUtilities.ContInfoPomPage;
import PomUtilities.ContactPomPage;
import PomUtilities.CreateNewContactPompage;
import PomUtilities.HomePomPage;
import PomUtilities.LoginPomPage;

public class CreateContact {

	@Test

	public static void main(String[] args) throws IOException, InterruptedException {
		// Fetch the data from property file

				PropertyFileUtility prop = new PropertyFileUtility();
				String BROWSER = prop.fetchDataFromPropFile("browser");
				String URL = prop.fetchDataFromPropFile("url");
				String USERNAME = prop.fetchDataFromPropFile("username");
				String PASSWORD = prop.fetchDataFromPropFile("password");
				String TIMEOUTS = prop.fetchDataFromPropFile("timeouts");

				// Generate the random num
				JavaUtility jutil = new JavaUtility();
				int randomint = jutil.generateRandomNumber();

				// Fetch the data from excel
				ExcelFileUtility ex_util = new ExcelFileUtility();
				String contname = ex_util.fetchDataFromExcelFile("contact", 1, 2) + randomint;

				// Launch the browser
				WebDriverUtility wutil = new WebDriverUtility();
				WebDriver driver = null;
				if (BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				} else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				} else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				} else {
					driver = new ChromeDriver();
				}
				// Maximize the window
				wutil.maximizeTheWindow(driver);

				// Implicit wait
				wutil.waitTillTheElementFound(driver, TIMEOUTS);

				// Navigate to an appln
				wutil.navigateToAnAppln(driver, URL);

				// Login to the appln
				LoginPomPage l = new LoginPomPage(driver);
				l.getUsernameTF(USERNAME);
				l.getPasswordTF(PASSWORD);
				l.getLogin_btn();

				// Click on contact tab in the homepage
				HomePomPage home = new HomePomPage(driver);
				home.getContact_tab();

				// Click on plus symbol
				ContactPomPage cp = new ContactPomPage(driver);
				cp.getContplusicon();

				// Enter contact name and click on save button
				CreateNewContactPompage cn = new CreateNewContactPompage(driver);
				cn.getLastnametf(contname);
				cn.getSave_btn();
				// verify the contact name
				ContInfoPomPage con_info = new ContInfoPomPage(driver);
				String header = con_info.getHeader();
				if (header.contains(contname)) {
					System.out.println("Test Pass");
				} else {
					System.out.println("Test Fail");
				}

				// Click on contact tab in the homepage
				home.getContact_tab();

				// Delete the contact
				driver.findElement(
						By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
						.click();

				Thread.sleep(3000);

				// Handle alert popup
				wutil.HandleAlertAndClickOnOk(driver);

				// Mouse hovering on admin icon
				WebElement admin = home.getAdminicon();
				wutil.mouseHoverOnAnElement(driver, admin);

				// Logout of the appln
				home.getSignout();

				// Close the browser
				wutil.quitTheBrowser(driver);

				// close excel
				ex_util.closeExcelWorkbook();
			}

		}
