package com.tvu.shqa.rpspage.test.encoderpagetest;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tvu.shqa.rpspage.page.encoderpage.HomePage;
import com.tvu.shqa.rpspage.page.encoderpage.LoginPage;
import com.tvu.shqa.rpspage.test.TestBaseClass;

public class LoginPageTest extends TestBaseClass{
	LoginPage loginPage;
	HomePage homePage;

	@Override
	public void initPage() {
		// TODO Auto-generated method stub
		loginPage = new LoginPage(driver);
	}
	
	@Test
	public void openLoginpageTest() {
		Assert.assertTrue(loginPage.isInLoginPage(), "Fail to open log in page after access login url");
		node.log(Status.PASS, "Success to open login page");
	}
	
	@Test
	public void loginTest() {
		loginPage.login(pros.getProperty("rps_encoder_username"), pros.getProperty("rps_encoder_password"));
		homePage = new HomePage(driver);
		//Close window if show alert message box
		homePage.closeWebPreviewAlert();
		Assert.assertTrue(homePage.isInHomePage(), "Not jump to home page after login.Fail to login");
		node.log(Status.PASS, "Jump to home page after login.Success to login");
	}

	@Override
	public void closeObject() {
		// TODO Auto-generated method stub
		loginPage = null;
		homePage = null;
	}
	
	

}
