package OrganizationModel;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import PomUtilities.CreateNewOrgPomPage;
import PomUtilities.HomePomPage;
import PomUtilities.OrgInfoPomPage;
import PomUtilities.OrganizationPomPage;

public class OrgScenariosTest {
	//@Listeners(ListenersUtility.Listeners.class)
	public class OrganizationScenariosTest extends BaseClass{
		//@Parameters("browser")
		//@Test(groups="smoke")
		@Test(retryAnalyzer = ListenersUtilities.IRetryAnalyser.class)
		public void createOrg() throws Exception {
			
			// Fetch the data from property file
			// FileInputStream fis = new
			// FileInputStream("./src/test/resources/CommonData.properties");
			// Properties p = new Properties();
			// p.load(fis);

//			PropertyFileUtility prop = new PropertyFileUtility();
//			String BROWSER = prop.fetchDataFrompropFile("browser");
//			String URL = prop.fetchDataFrompropFile("url");
//			String USERNAME = prop.fetchDataFrompropFile("username");
//			String PASSWORD = prop.fetchDataFrompropFile("password");
//			String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");

			// Generate the random num
			JavaUtility jutil = new JavaUtility();
			int randomint = jutil.generateRandomNumber();

			// Fetch the data from excel
			// FileInputStream efis = new
			// FileInputStream("./src/test/resources/TestData.xlsx");
			// Workbook wb = WorkbookFactory.create(efis);
			// String orgname = wb.getSheet("organization").getRow(1).getCell(2).toString();
			ExcelFileUtility ex_util = new ExcelFileUtility();
			String orgname = ex_util.fetchDataFromExcelFile("organization", 1, 2) + randomint;
			
//create organiztion Test
			// Launch the browser
			WebDriverUtility wutil = new WebDriverUtility();
//			WebDriver driver = null;
//			if (BROWSER.equals("chrome")) {
//				driver = new ChromeDriver();
//			} else if (BROWSER.equals("edge")) {
//				driver = new EdgeDriver();
//			} else if (BROWSER.equals("firefox")) {
//				driver = new FirefoxDriver();
//			} else {
//				driver = new ChromeDriver();
//			}
	//
//			// Maximize the window
//			// driver.manage().window().maximize();
//			wutil.maximizeTheWindow(driver);
	//
//			// Implicit Wait
//			// long time=Long.parseLong(TIMEOUTS);
//			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//			wutil.waitTillTheElementFound(driver, TIMEOUTS);
	//
//			// Navigate to the Application
//			// driver.get(URL);
//			wutil.navigateToAnAppln(driver, URL);
	//
//			// Login to the Application
//			// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//			// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//			// driver.findElement(By.id("submitButton")).click();
//			LoginPomPage l = new LoginPomPage(driver);
//			l.getUsernameTF(USERNAME);
//			l.getPasswordTF(PASSWORD);
//			l.getLogin_btn();

			// Click on Organization tab in the home page
			// driver.findElement(By.linkText("Organizations")).click();
			HomePomPage home = new HomePomPage(driver);
			home.getOrg_tab();

			// Click on plus(+) symbol
			// driver.findElement(By.xpath("//img[@title=\"Create
			// Organization...\"]")).click();
			OrganizationPomPage org = new OrganizationPomPage(driver);
			org.getOrgplusicon();

			// Enter Organization Name and click on Save button
			// driver.findElement(By.name("accountname")).sendKeys(orgname);
			// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
			CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
			cno.getOrgnameTF(orgname);
			cno.getSave_btn();

			// verify the organization name
			// WebElement header =
			// driver.findElement(By.xpath("//span[contains(text(),'Organization
			// Information')]"));
			OrgInfoPomPage oip = new OrgInfoPomPage(driver);
			String org_header = oip.getHeader();
			if (org_header.contains(orgname)) {
				System.out.println("Test Pass");
			} else {
				System.out.println("Test Fail");
			}

			// Click on Organization tab in the home page
			// driver.findElement(By.linkText("Organizations")).click();
			home.getOrg_tab();

			// Delete the organization
			driver.findElement(
					By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
					.click();

			Thread.sleep(3000);

			// Handle alert pop-up
			// driver.switchTo().alert().accept();
			wutil.HandleAlertAndClickOnOk(driver);

			// Mouse hovering on Admin icon
			// WebElement admin = driver.findElement(By.xpath("//span[text()='
			// Administrator']/../../td/img"));
//			WebElement admin = home.getAdminicon();
//			wutil.mouseHoverOnAnElement(driver, admin);
	//
//			// Actions act = new Actions(driver);
//			// act.moveToElement(admin).perform();
	//
//			// Logout of the application
//			// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//			home.getSignout();
	//
//			// Close the Browser
//			// driver.quit();
//			wutil.quitTheBrowser(driver);

			// Close the Excel Sheet
			// wb.close();
			ex_util.closeExcelWorkbook();

		}


		//		@Parameters("browser")
			@Test(groups="smoke")

			public void CreateOrganization_IndustryAndType() throws IOException, InterruptedException {

				// Fetch the data from property file
				// FileInputStream fis = new
				// FileInputStream("./src/test/resources/CommonData.properties");
				// Properties p = new Properties();
				// p.load(fis);
//				PropertyFileUtility prop = new PropertyFileUtility();
//				String BROWSER = prop.fetchDataFrompropFile("browser");
//				String URL = prop.fetchDataFrompropFile("url");
//				String USERNAME = prop.fetchDataFrompropFile("username");
//				String PASSWORD = prop.fetchDataFrompropFile("password");
//				String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");

				// Generate the random number
				JavaUtility jutil = new JavaUtility();
				int randomint = jutil.generateRandomNumber();

				// Fetch the data from Excel file
				// FileInputStream efis = new
				// FileInputStream("./src/test/resources/TestData.xlsx");
				// Workbook wb = WorkbookFactory.create(efis);
				ExcelFileUtility ex_util = new ExcelFileUtility();
				String orgname = ex_util.fetchDataFromExcelFile("organization", 7, 2) + randomint;
				String Ind = ex_util.fetchDataFromExcelFile("organization", 7, 3);
				String Type = ex_util.fetchDataFromExcelFile("organization", 7, 4);

				// Launch the browser
//				WebDriver driver = null;
//				if (BROWSER.equals("chrome")) {
//					driver = new ChromeDriver();
//				} else if (BROWSER.equals("edge")) {
//					driver = new EdgeDriver();
//				} else if (BROWSER.equals("firefox")) {
//					driver = new FirefoxDriver();
//				} else {
//					driver = new ChromeDriver();
//				}

				WebDriverUtility wutil = new WebDriverUtility();

				// Maximize
//				// driver.manage().window().maximize();
//				wutil.maximizeTheWindow(driver);
	//
//				// Implicitly Wait
//				// long time = Long.parseLong(TIMEOUTS);
//				// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//				wutil.waitTillTheElementFound(driver, TIMEOUTS);
	//
//				// Navigate to the Application
//				// driver.get(URL);
//				wutil.navigateToAnAppln(driver, URL);
	//
//				// Login to the Application
//				// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//				// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//				// driver.findElement(By.id("submitButton")).click();
//				LoginPomPage l = new LoginPomPage(driver);
//				l.getUsernameTF(USERNAME);
//				l.getPasswordTF(PASSWORD);
//				l.getLogin_btn();

				// Click on organization tab in the home page
				// driver.findElement(By.linkText("Organizations")).click();
				HomePomPage home = new HomePomPage(driver);
				home.getOrg_tab();

				// Click on the plus(+) Symbol
				// driver.findElement(By.xpath("//img[@title=\"Create
				// Organization...\"]")).click();
				OrganizationPomPage org = new OrganizationPomPage(driver);
				org.getOrgplusicon();

				// Enter organization name and click on save button
				// driver.findElement(By.name("accountname")).sendKeys(orgname);
				// WebElement industryDD = driver.findElement(By.name("industry"));
				// Select si = new Select(industryDD);
				// si.selectByValue(ind);
				CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
				cno.getOrgnameTF(orgname);

				WebElement industryDD = cno.getInd_DD();
				wutil.SelectDDByValue(industryDD, Ind);

				WebElement typeDD = cno.getType_DD();
				wutil.SelectDDByValue(typeDD, Type);

				// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
				cno.getSave_btn();
				// Verify the organization name
				// WebElement header =
				// driver.findElement(By.xpath("//span[contains(text(),'Organization
				// Information')]"));
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
					System.out.println("Verified Ind dd : Pass");
				} else {
					System.out.println("Verified ind dd : Fail");
				}

				// Verify Type
				String Acttype = oip.getVerifytypeDD();
				if (Acttype.equals(Type)) {
					System.out.println("Verified Type dd : Pass");
				} else {
					System.out.println("Verified Type dd : Fail");
				}

				// Click on organization tab in the Home Page
				// driver.findElement(By.linkText("Organizations")).click();
				home.getOrg_tab();

				// Delete the Organization
				driver.findElement(By
						.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
						.click();

				Thread.sleep(3000);

				// Handle alert pop-up
				// driver.switchTo().alert().accept();
				wutil.HandleAlertAndClickOnOk(driver);

				// Mouse hovering on Admin icon
//				// WebElement admin = driver.findElement(By.xpath("//span[text()='
//				// Administrator']/../../td/img"));
//				WebElement admin = home.getAdminicon();
//				wutil.mouseHoverOnAnElement(driver, admin);
	//
//				// Actions act = new Actions(driver);
//				// act.moveToElement(admin).perform();
	//
//				// Logout of the Application
//				// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//				home.getSignout();
	//
//				// Close the browser
//				// driver.quit();
//				wutil.quitTheBrowser(driver);

				// Close the Excel Sheet
				// wb.close();
				ex_util.closeExcelWorkbook();
			}

//				@Parameters("browser")
				@Test(groups="smoke")

				public void createOrganizationWithPhoneNumber() throws IOException, InterruptedException {

					// Fetch the data from property file
					// FileInputStream fis = new
					// FileInputStream("./src/test/resources/CommonData.properties");
					// Properties p = new Properties();
//					// p.load(fis);
//					PropertyFileUtility prop = new PropertyFileUtility();
//					String BROWSER = prop.fetchDataFrompropFile("browser");
//					String URL = prop.fetchDataFrompropFile("url");
//					String USERNAME = prop.fetchDataFrompropFile("username");
//					String PASSWORD = prop.fetchDataFrompropFile("password");
//					String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");

					// Generate the random number
					JavaUtility jutil = new JavaUtility();
					int randomint = jutil.generateRandomNumber();

					// Fetch the data from Excel file
					// FileInputStream efis = new
					// FileInputStream("./src/test/resources/TestData.xlsx");
					// Workbook wb = WorkbookFactory.create(efis);
					// String orgname = wb.getSheet("organization").getRow(4).getCell(2).toString();
					// String phno = wb.getSheet("organization").getRow(4).getCell(3).toString();
					ExcelFileUtility ex_util = new ExcelFileUtility();
					String orgname = ex_util.fetchDataFromExcelFile("organization", 4, 3) + randomint;
					String phno = ex_util.fetchDataFromExcelFile("organization", 4, 3);

					// Launch the browser
//					WebDriver driver = null;
//					if (BROWSER.equals("chrome")) {
//						driver = new ChromeDriver();
//					} else if (BROWSER.equals("edge")) {
//						driver = new EdgeDriver();
//					} else if (BROWSER.equals("firefox")) {
//						driver = new FirefoxDriver();
//					} else {
//						driver = new ChromeDriver();
//					}

					WebDriverUtility wutil = new WebDriverUtility();

					// Maximize the window
					// driver.manage().window().maximize();
//					wutil.maximizeTheWindow(driver);
	//
//					// Implicitly Wait
//					// long time = Long.parseLong(TIMEOUTS);
//					// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//					wutil.waitTillTheElementFound(driver, TIMEOUTS);
	//
//					// Navigate to the Application
//					// driver.get(URL);
//					wutil.navigateToAnAppln(driver, URL);
	//
//					// Login to the Application
//					// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//					// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//					// driver.findElement(By.id("submitButton")).click();
//					LoginPomPage l = new LoginPomPage(driver);
//					l.getUsernameTF(USERNAME);
//					l.getPasswordTF(PASSWORD);
//					l.getLogin_btn();

					// Click on organization tab in the home page
					// driver.findElement(By.linkText("Organizations")).click();
					HomePomPage home = new HomePomPage(driver);
					home.getOrg_tab();

					// Click on the plus(+) symbol/icon
					// driver.findElement(By.xpath("//img[@title=\"Create
					// Organization...\"]")).click();
					OrganizationPomPage org = new OrganizationPomPage(driver);
					org.getOrgplusicon();

					// Enter organization name and click on save button
					// driver.findElement(By.name("accountname")).sendKeys(orgname);
					// driver.findElement(By.id("phone")).sendKeys(phno);
					// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
					CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
					cno.getOrgnameTF(orgname);
					cno.getPhnoTF(phno);
					cno.getSave_btn();

					// Verify the organization name
					// WebElement header =
					// driver.findElement(By.xpath("//span[contains(text(),'Organization
					// Information')]"));
					OrgInfoPomPage oip = new OrgInfoPomPage(driver);
					String org_header = oip.getHeader();
					if (org_header.contains(orgname)) {
						System.out.println("Test Pass");
					} else {
						System.out.println("Test Fail");
					}

					// Verify Phone Number
					// WebElement phnoTF = driver.findElement(By.id("dtlview_Phone"));
					String phnoTF = oip.getVerifyphno();
					if (phnoTF.equals(phno)) {
						System.out.println("Verified ph no : Pass");
					} else {
						System.out.println("Verified ph no : Fail");
					}

					// Click on organization tab in the Home Page
					// driver.findElement(By.linkText("Organizations")).click();
					home.getOrg_tab();

					// Delete the Organization
					driver.findElement(By.xpath(
							"//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
							.click();

					Thread.sleep(3000);

					// Handle alert pop-up
					// driver.switchTo().alert().accept();
					wutil.HandleAlertAndClickOnOk(driver);

					// Mouse hovering on Admin icon
					// WebElement admin = driver.findElement(By.xpath("//span[text()='
//					// Administrator']/../../td/img"));
//					WebElement admin = home.getAdminicon();
//					wutil.mouseHoverOnAnElement(driver, admin);
	//
//					// Actions act = new Actions(driver);
//					// act.moveToElement(admin).perform();
	//
//					// Logout of the Application
//					// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//					home.getSignout();
	//
//					// Close the browser
//					// driver.quit();
//					wutil.quitTheBrowser(driver);

					// Close excel workbook
					ex_util.closeExcelWorkbook();
				}
			
		
	}
	}