package Leadsmodel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateLeads {
	@Test

		public static void main(String[] args) throws IOException, InterruptedException {
		// fetch the data from the properties files
				FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
				Properties p = new Properties();
				p.load(fis);

				String BROWSER = p.getProperty("browser");
				String URL = p.getProperty("url");
				String USERNAME = p.getProperty("username");
				String PASSWORD = p.getProperty("password");
				String TIMEOUTS = p.getProperty("timeouts");

				// fetch the data from the Excel
				FileInputStream efis = new FileInputStream("./src/test/resources/VTigerTestdata.xlsx");
				Workbook wb = WorkbookFactory.create(efis);
				String Leadname = wb.getSheet("Leads").getRow(1).getCell(2).toString();

				//launch the browser
				WebDriver driver=null;
				if(BROWSER.equals("chrome")) {
				driver = new ChromeDriver();
				}
				else if(BROWSER.equals("edge")){
					driver=new EdgeDriver();
				}else if(BROWSER.equals("edge")){
					driver=new FirefoxDriver();
				}else {
					driver=new ChromeDriver();
				
				}
				

				// Maximize the Webpage
				driver.manage().window().maximize();

				// Implict wait
				long time = Long.parseLong(TIMEOUTS);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

				// navigate to url
				driver.get(URL);

				// login page
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// click on leads tab in homepage
				driver.findElement(By.linkText("Leads")).click();
				
				// click on plus Symbol
				driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
				
				// enter the Lead name company and save button
				driver.findElement(By.name("lastname")).sendKeys(Leadname);
				driver.findElement(By.name("company")).sendKeys(Leadname);
				driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
				// verify the lead name
				WebElement header = driver.findElement(By.xpath("//span[contains(text(), 'Lead Information')]"));
				if (header.getText().contains(Leadname)) {
					System.out.println("Test Pass");
				} else {
					System.out.println("Test Fail");
				}
				// click on contact tab in homepage
				driver.findElement(By.linkText("Leads")).click();

				// delete the Leads
				driver.findElement(
						By.xpath("//a[text()='" + Leadname 	+ "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
					
				Thread.sleep(3000);

				// handle alert popup
				driver.switchTo().alert().accept();
				
			// Mouse hovering on admin icon
			  WebElement admin = driver.findElement(By.xpath("//span[text()=' Administrator']/../../td/img"));

			    Actions act = new Actions(driver);
				act.moveToElement(admin).perform();

				// logout of the appln
				driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

				// close the browser
				driver.quit();
				
				
	}

}
