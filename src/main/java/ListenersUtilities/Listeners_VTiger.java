package ListenersUtilities;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassUtility.BaseClass;

public class Listeners_VTiger implements ITestListener, ISuiteListener {
	public class Listeners_Vtiger implements ITestListener, ISuiteListener {
		public ExtentSparkReporter spark;
		public static ExtentTest test;
		public ExtentReports report;
		
		@Override
		public void onStart(ISuite suite) {
			Reporter.log("On Suite Execution started", true);
			Reporter.log("Configure the Report", true);
			//Configure the report
			String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
			spark = new ExtentSparkReporter("./AdvancedReport/Report"+timestamp+".html");
			spark.config().setDocumentTitle("Vtiger CRM appln");
			spark.config().setReportName("Vtiger");
			spark.config().setTheme(Theme.DARK);
			
			report = new ExtentReports();
			report.attachReporter(spark);
			report.setSystemInfo("OS", "Windows-11");
			report.setSystemInfo("Browser", "Chrome-137");
			
			//ExtentTest test = report.createTest("Test name");
			//test.log(Status.INFO, "Test");
			//test.log(Status.PASS, "Test Pass");
			//test.log(Status.FAIL, "Test Fail");
		} 
		
		@Override
		public void onFinish(ISuite suite) {
			Reporter.log("On Suite Execution finished", true);
			Reporter.log("Report Backup", true);
			report.flush();
		}
		
		@Override
		public void onTestStart(ITestResult result) {
			Reporter.log("On Test Execution started", true);
			String testname = result.getMethod().getMethodName();
			test = report.createTest(testname);
			UtilityClassObject.setTest(test);
			test.log(Status.INFO, "Test method execution started <==" + testname + "==>");
		}

		@Override
		public void onTestSuccess(ITestResult result) {
			Reporter.log("On Test Execution success", true);
			String testname = result.getMethod().getMethodName();
			test.log(Status.INFO, "Test method execution successful <==" + testname + "==>");
		}
		
		@Override
		public void onTestFailure(ITestResult result) {
			Reporter.log("On Test Execution Failed", true);
			String testname = result.getMethod().getMethodName();
			String timestamp = new Date().toString().replace(" ", "_").replace(":", "_"); 
			
			// Take screenshot
			TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
			String src = ts.getScreenshotAs(OutputType.BASE64);
			test.addScreenCaptureFromBase64String(src + "" + testname + "_" + timestamp + "");
			test.log(Status.INFO, "Test method execution Failed <==" + testname + "==>");
			
		}
		
		@Override
		public void onTestSkipped(ITestResult result) {
			Reporter.log("Test Execution Skipped", true);
			String testname = result.getMethod().getMethodName();
			test.log(Status.INFO, "test method execution skipped <==" + testname + "==>");
		}
	}
}	



