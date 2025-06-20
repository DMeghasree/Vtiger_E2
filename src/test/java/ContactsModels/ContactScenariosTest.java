package ContactsModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import PomUtilities.ContInfoPomPage;
import PomUtilities.ContactPomPage;
import PomUtilities.CreateNewContactPompage;
import PomUtilities.CreateNewOrgPomPage;
import PomUtilities.HomePomPage;
import PomUtilities.OrgInfoPomPage;
import PomUtilities.OrganizationPomPage;
//changes done to check pull
@Listeners(ListenersUtility.Listeners.class)
public class ContactScenariosTest extends BaseClass{
	//@Parameters("browser")
	//@Test(groups="smoke")
	@Test(retryAnalyzer = ListenersUtilities.IRetryAnalyser.class)
	public void create_ContTest() throws Exception {

		// Fetch the data from property file
		// FileInputStream fis = new
		// FileInputStream("./src/test/resources/CommonData.properties");
		// Properties p = new Properties();
		// p.load(fis);

//		PropertyFileUtility prop = new PropertyFileUtility();
//		String BROWSER = prop.fetchDataFrompropFile("browser");
//		String URL = prop.fetchDataFrompropFile("url");
//		String USERNAME = prop.fetchDataFrompropFile("username");
//		String PASSWORD = prop.fetchDataFrompropFile("password");
//		String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");

		// Fetching the random number/Generate the random number
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from Excel file
		// FileInputStream efis = new
		// FileInputStream("./src/test/resources/TestData.xlsx");
		// Workbook wb = WorkbookFactory.create(efis);
		// String contname = wb.getSheet("contact").getRow(1).getCell(2).toString();
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 1, 2) + randomint;

		// Launch the browser
		WebDriverUtility wutil = new WebDriverUtility();
//		WebDriver driver = null;
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}

		// Maximize the window
		// driver.manage().window().maximize();
//		wutil.maximizeTheWindow(driver);
//
//		// Implicitly Wait
//		// long time = Long.parseLong(TIMEOUTS);
//		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//		wutil.waitTillTheElementFound(driver, TIMEOUTS);
//
//		// Navigate to the Application
//		// driver.get(URL);
//		wutil.navigateToAnAppln(driver, URL);

		// Login to the Application
		// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		// driver.findElement(By.id("submitButton")).click();
//		LoginPomPage l = new LoginPomPage(driver);
//		l.getUsernameTF(USERNAME);
//		l.getPasswordTF(PASSWORD);
//		l.getLogin_btn();

		// Click on organization tab in the home page
		// driver.findElement(By.linkText("Contacts")).click();
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on the (+) plus Symbol
		// driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		ContactPomPage cp = new ContactPomPage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		// driver.findElement(By.name("contname")).sendKeys(contname);
		// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		cn.getLastnametf(contname);
		cn.getSave_btn();

		// verify the Contact name
		// WebElement header =
		// driver.findElement(By.xpath("//span[contains(text(),'Contact
		// Information')]"));
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String header = con_info.getHeader();
		if (header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Click on Contact tab in the home page
		// driver.findElement(By.linkText("Contacts")).click();
		home.getContact_tab();

		// Delete the Contact
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert pop-up
		// driver.switchTo().alert().accept();
		wutil.HandleAlertAndClickOnOk(driver);

		// Mouse hovering on Admin icon
		// WebElement admin = driver.findElement(By.xpath("//span[text()='
		// Administrator']/../../td/img"));
//		WebElement admin = home.getAdminicon();
//		wutil.mouseHoverOnAnElement(driver, admin);
//
//		// Actions act = new Actions(driver);
//		// act.moveToElement(admin).perform();
//
//		// Logout of the application
//		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		home.getSignout();
//
//		// Close the Browser
//		// driver.quit();
//		wutil.quitTheBrowser(driver);

		// Close the Excel Sheet
		ex_util.closeExcelWorkbook();

	}

//	@Parameters("browser")
//	@Test(groups="smoke")
	
	@Test(retryAnalyzer = ListenersUtilities.IRetryAnalyser.class)

	public void CreateContact_WithOrganization() throws Exception {

		// Fetch the data from property file
		// FileInputStream fis = new
		// FileInputStream("./src/test/resources/CommonData.properties");
		// Properties p = new Properties();
//		// p.load(fis);
//		PropertyFileUtility prop = new PropertyFileUtility();
//		String BROWSER = prop.fetchDataFrompropFile("browser");
//		String URL = prop.fetchDataFrompropFile("url");
//		String USERNAME = prop.fetchDataFrompropFile("username");
//		String PASSWORD = prop.fetchDataFrompropFile("password");
//		String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");
//
		// Generate the random Number
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		// FileInputStream efis = new
		// FileInputStream("./src/test/resources/TestData.xlsx");
		// Workbook wb = WorkbookFactory.create(efis);
		// String contname = wb.getSheet("contact").getRow(1).getCell(2).toString();
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 7, 3) + randomint;
		String orgname = ex_util.fetchDataFromExcelFile("contact", 7, 2) + randomint;

//		// Launch the browser
//		WebDriver driver = null;
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}

		WebDriverUtility wutil = new WebDriverUtility();

		// Maximize the window
//		// driver.manage().window().maximize();
//		wutil.maximizeTheWindow(driver);
//
//		// Implicitly Wait
//		// long time = Long.parseLong(TIMEOUTS);
//		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//		wutil.waitTillTheElementFound(driver, TIMEOUTS);
//
//		// Navigate to the Application
//		// driver.get(URL);
//		wutil.navigateToAnAppln(driver, URL);
//
//		// Login to the Application
//		// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		// driver.findElement(By.id("submitButton")).click();
//		LoginPomPage l = new LoginPomPage(driver);
//		l.getUsernameTF(USERNAME);
//		l.getPasswordTF(PASSWORD);
//		l.getLogin_btn();

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
		// driver.findElement(By.name("accountname")).sendKeys(contname);
		// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
		CreateNewOrgPomPage cno = new CreateNewOrgPomPage(driver);
		cno.getOrgnameTF(orgname);
		cno.getSave_btn();

		// verify the organization name
		// WebElement orgheader =
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
		driver.findElement(By.linkText("Organizations")).click();

		// Click on Contacts tab in the home page
		// driver.findElement(By.linkText("Contacts")).click();
		home.getContact_tab();

		// Click on the plus(+) icon
		// driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		ContactPomPage cp = new ContactPomPage(driver);
		cp.getContplusicon();

		// Enter contact name and click on save button
		// driver.findElement(By.name("accountname")).sendKeys(orgname);
		// driver.findElement(By.xpath("//input[@value=' Save ']")).click();
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		cn.getLastnametf(contname);
		String pwid = wutil.fetchParentWindowId(driver);
		cn.getOrgplusicon();
		wutil.switchToChildWindowBasedOnUrl(driver, "module=Accounts&action");

		cn.getOrgSearchTF(orgname);
		cn.getOrgSearchbtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wutil.switchToParentWindow(driver, pwid);

		cn.getSave_btn();

		// verify the contact name
		// WebElement org_header =
		// driver.findElement(By.xpath("//span[contains(text(),'Organization
		// Information')]"));
		String con_header = con_info.getHeader();
		if (con_header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Verify org in contact info page
		String verifyorg = driver
				.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a[text()='" + orgname + "']")).getText();
		if (verifyorg.contains(orgname)) {
			System.out.println("contact successfully created with org");
		} else {
			System.out.println("contact has not been created with proper org");
		}

		// Click on the (+) plus symbol
		// driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		// ContactPomPage cp = new ContactPomPage(driver);
		// cp.getContplusicon();

		// Enter contact name and click on save button
		// driver.findElement(By.name("lastname")).sendKeys(contname);
		// String pwid =wutil.fetchParentWindowId(driver);
		// driver.findElement(By.xpath("//img[@title='Select']")).click();

		// wutil.switchToChildWindowBasedOnUrl(driver, "module=Accounts&action");

		// driver.findElement(By.id("search_txt")).sendKeys(orgname);
		// driver.findElement(By.name("search")).click();
		// driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		// wutil.switchToParentWindow(driver, pwid);

		// driver.findElement(By.xpath("//input[@value=' Save ']")).click();

		// verify the Contact name
		// WebElement header =
		// driver.findElement(By.xpath("//span[contains(text(),'Contact
		// Information')]"));
		// if(header.getText().contains(contname)) {
		// System.out.println("Test Pass");
		// } else {
		// System.out.println("Test Fail");
		// }

		// Verify organization in contact information page
		// String verifyorg = driver
		// .findElement(By.xpath("//td[@id='mouseArea_Organization name']/a[text()='" +
		// orgname + "']")).getText();
		// if (verifyorg.contains(orgname)) {
		// System.out.println("contact successfully created with org");
		// } else {
		// System.out.println("contact has not created with proper org");
		// }

		// Click the contact tab in the homepage
		// driver.findElement(By.linkText("contacts")).click();
		home.getContact_tab();

		// Delete the Contact
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert pop-up
		// driver.switchTo().alert().accept();
		wutil.HandleAlertAndClickOnOk(driver);

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
//		WebElement admin = home.getAdminicon();
//		wutil.mouseHoverOnAnElement(driver, admin);
//
//		// Actions act = new Actions(driver);
//		// act.moveToElement(admin).perform();
//
//		wutil.mouseHoverOnAnElement(driver, admin);
//
//		// Logout of the application
//		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		home.getSignout();
//
//		// Close the Browser
//		// driver.quit();
//		wutil.quitTheBrowser(driver);

		// Close the Excel Sheet
		ex_util.closeExcelWorkbook();
	}

//	@Parameters("browser")
//	@Test(groups="smoke")
	
	@Test(retryAnalyzer = ListenersUtilities.IRetryAnalyser.class)

	public void createContWithDate() throws Exception {

//		// fetch the data from property File
//		PropertyFileUtility prop = new PropertyFileUtility();
//		String BROWSER = prop.fetchDataFrompropFile("browser");
//		String URL = prop.fetchDataFrompropFile("url");
//		String USERNAME = prop.fetchDataFrompropFile("username");
//		String PASSWORD = prop.fetchDataFrompropFile("password");
//		String TIMEOUTS = prop.fetchDataFrompropFile("timeouts");

		// Generate the random number
		JavaUtility jutil = new JavaUtility();
		int randomint = jutil.generateRandomNumber();

		// Fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contname = ex_util.fetchDataFromExcelFile("contact", 4, 2) + randomint;

		// launch the browser
		WebDriverUtility wutil = new WebDriverUtility();
//		WebDriver driver = null;
//		if (BROWSER.equals("chrome")) {
//			driver = new ChromeDriver();
//		} else if (BROWSER.equals("edge")) {
//			driver = new EdgeDriver();
//		} else if (BROWSER.equals("firefox")) {
//			driver = new FirefoxDriver();
//		} else {
//			driver = new ChromeDriver();
//		}

		// Maximize the window
//		// driver.manage().window().maximize();
//		wutil.maximizeTheWindow(driver);
//
//		// Implicitly Wait
//		// long time = Long.parseLong(TIMEOUTS);
//		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//		wutil.waitTillTheElementFound(driver, TIMEOUTS);
//
//		// Navigate to the Application
//		// driver.get(URL);
//		wutil.navigateToAnAppln(driver, URL);
//
//		// Login to the Application
//		// driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		// driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		// driver.findElement(By.id("submitButton")).click();
//		LoginPomPage l = new LoginPomPage(driver);
//		l.getUsernameTF(USERNAME);
//		l.getPasswordTF(PASSWORD);
//		l.getLogin_btn();

		// Click on organization tab in the home page
		// driver.findElement(By.linkText("Contacts")).click();
		HomePomPage home = new HomePomPage(driver);
		home.getContact_tab();

		// Click on the Symbol
		// driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();
		ContactPomPage cp = new ContactPomPage(driver);
		cp.getContplusicon();

		// Enter contact name and pass the support date
		// driver.findElement(By.name("lastname")).sendKeys(contname);
		// WebElement strt_suppdate = driver.findElement(By.name("support_start_date"));
		CreateNewContactPompage cn = new CreateNewContactPompage(driver);
		cn.getLastnametf(contname);

		WebElement strt_suppdate = cn.getStartdatetf();
		strt_suppdate.clear();

		// Fetching the current date
		String strtdate = jutil.getCurrentSystemdate();
		strt_suppdate.sendKeys(strtdate);

		WebElement end_suppdate = cn.getEnddatetf();
		end_suppdate.clear();

		// Fetching date after 30 days
		// WebElement end_suppdate = driver.findElement(By.name("support_end_date"));
		String enddate = jutil.getDateAfterGivenDays(30);
		end_suppdate.sendKeys(enddate);

		cn.getSave_btn();

		// driver.findElement(By.xpath("//input[@value=' Save ']")).click();

		// verify the Contact name
		// WebElement header =
		// driver.findElement(By.xpath("//span[contains(text(),'Contact
		// Information')]"));
		ContInfoPomPage con_info = new ContInfoPomPage(driver);
		String header = con_info.getHeader();
		if (header.contains(contname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

		// Verify Support start date
		// WebElement strt_date = driver.findElement(By.id("dtlview_Support Start
		// Date"));
		String strt_date = con_info.getVerifyStartDate();
		if (strt_date.contains(strtdate)) {
			System.out.println("Successfully created strt date");
		} else {
			System.out.println("Not created strt date");
		}

		// Verify Support end date
		// WebElement end_date = driver.findElement(By.id("dtlview_Support End Date"));
		String end_date = con_info.getVerifyEndDate();
		if (end_date.contains(enddate)) {
			System.out.println("Successfully created end date");
		} else {
			System.out.println("Not created end date");
		}

		// Click on Contact tab in the home page
		// driver.findElement(By.linkText("Contacts")).click();
		home.getContact_tab();

		// Delete the contact
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle alert pop-up
		wutil.HandleAlertAndClickOnOk(driver);

		// Mouse Hovering on admin icon
		// WebElement admin = driver.findElement(By.xpath("//span[text()='
		// Administrator']/../../td/img"));
//		WebElement admin = home.getAdminicon();
//		wutil.mouseHoverOnAnElement(driver, admin);
//
//		// Logout of the Application
//		// driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		home.getSignout();
//
//		// Close the browser
//		wutil.quitTheBrowser(driver);

		// Close the Excel Workbook
		ex_util.closeExcelWorkbook();
	}

}
