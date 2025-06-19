package OrganizationModel;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
import PomUtilities.CreateNewOrgPomPage;
import PomUtilities.HomePomPage;
import PomUtilities.LoginPomPage;
import PomUtilities.OrgInfoPomPage;
import PomUtilities.OrganizationPomPage;

public class CreateOrganizationAndType {
	@Test
	

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
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

				// Fetch data from Excel file
				ExcelFileUtility ex_util = new ExcelFileUtility();
				String orgname = ex_util.fetchDataFromExcelFile("organization", 7, 2) + randomint;
				String Ind = ex_util.fetchDataFromExcelFile("organization", 7, 3);
				String Type = ex_util.fetchDataFromExcelFile("organization", 7, 4);

				// Launch the browser
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

				WebDriverUtility wutil = new WebDriverUtility();

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

				// Click on org tab in the homepage
				HomePomPage home = new HomePomPage(driver);
				home.getOrg_tab();

				// Click on plus symbol
				OrganizationPomPage org = new OrganizationPomPage(driver);
				org.getOrgplusicon();

				// Enter org name and click on save button
				CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
				cno.getOrgnameTF(orgname);

				WebElement industryDD = cno.getInd_DD();
				wutil.SelectDDByValue(industryDD, Ind);

				WebElement typeDD = cno.getType_DD();
				wutil.SelectDDByValue(typeDD, Type);

				cno.getSave_btn();

				// verify the org name
				OrgInfoPomPage oip = new OrgInfoPomPage(driver);
				String org_header = oip.getHeader();
				if (org_header.contains(orgname)) {
					System.out.println("Test Pass");
				} else {
					System.out.println("Test Fail");
				}

				// Verify Industry
				String Actind = oip.getVerifyindDD();
				if (Actind.equals(Ind)) {
					System.out.println("Verified Industry : Pass");
				} else {
					System.out.println("Verified Industry : Fail");
				}

				// verify Type
				String Acttype = oip.getVerifytypeDD();
				if (Acttype.equals(Type)) {
					System.out.println("Verified Type : Pass");
				} else {
					System.out.println("Verified Type : Fail");
				}

				// Click on org tab in the homepage
				home.getOrg_tab();
				// Delete the org
				driver.findElement(
						By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
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
