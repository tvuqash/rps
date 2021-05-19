package com.tvu.shqa.rpspage.test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tvu.shqa.rpspage.utils.ConfigFileManager;
import com.tvu.shqa.rpspage.utils.CustomWebDriverManager;
import com.tvu.shqa.rpspage.utils.DateTimeClass;
import com.tvu.shqa.rpspage.utils.ExtentManager;

public abstract class TestBaseClass extends ExtentManager {
	public static WebDriver driver;
	public static Properties pros;
	
	@BeforeSuite
	public void beforeSuite() {
		setReport();
		pros = new ConfigFileManager("config.properties").getPros();
	}
	
	@BeforeTest
	public void beforeTest(ITestContext tc) {
		driver = new CustomWebDriverManager(pros.getProperty("browser"),false).getWebDriver();
		setSuite(tc.getName());
		driver.get(pros.getProperty("rps_encoder_url"));
	}
	
	@BeforeClass
	public void beforeClass() {
		setTest(this.getClass().getSimpleName());
		initPage();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		setNode(method.getName());
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result){
		if (ITestResult.FAILURE == result.getStatus()) {
			node.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " is failed", ExtentColor.RED));
			node.log(Status.INFO,MarkupHelper.createLabel(result.getThrowable() + "- Test Case Failed", ExtentColor.RED));

			//test.addScreenCaptureFromPath(getScreenShot(result.getName()), "Picture:");
			//test.fail("Attached picture:",MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
			node.log(Status.INFO, "Attached picture:",MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
		} else if (ITestResult.SUCCESS == result.getStatus()) {
			node.log(Status.PASS, "Test case:" + result.getName() + " is passed");
		} else if (ITestResult.SKIP == result.getStatus()) {
			//node.log(Status.SKIP, "Test case:" + result.getName() + " is skipped");
			node.log(Status.SKIP,MarkupHelper.createLabel(result.getThrowable() + "- Test Case Skipped", ExtentColor.ORANGE));
			node.log(Status.INFO, "Attached picture:",MediaEntityBuilder.createScreenCaptureFromPath(getScreenShot(result.getName())).build());
		}

		flushReport(); 
	}
	
	@AfterClass
	public void afterClass() {
		closeObject();
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public static String getScreenShot(String fileName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String dateTime = DateTimeClass.getDateTime();
		fileName = fileName + "_" + dateTime + ".png";
		String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + fileName;
		//String imageUrl = "http://127.0.0.1:18080/jenkins/job/ExtentDemo1/ws/screenshots/" + fileName;
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (Exception e) {
			e.getMessage();
		}

		//return imageUrl;
		return destination;
	}

	public abstract void initPage();
	public abstract void closeObject();
}
