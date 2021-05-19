package com.tvu.shqa.rpspage.test.encoderpagetest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tvu.shqa.rpspage.page.encoderpage.HomePage;
import com.tvu.shqa.rpspage.test.TestBaseClass;

public class HomePageTest extends TestBaseClass {
	HomePage homePage;

	@Override
	public void initPage() {
		// TODO Auto-generated method stub
		homePage = new HomePage(driver);
		
	}
	
	@Test
	public void startAllLiveTest() {
		homePage.refreshPage();
		homePage.clkStartAllBtn();
		homePage.sleepTime(1000);
		Assert.assertTrue(homePage.isStartAll(), "Start All button doesn't change to Stop All button");
		node.log(Status.PASS, "Start All button change to Stop All button");
		Assert.assertTrue(homePage.isDelayInputDisabled(), "Delay input is not disabled after start All live");
		node.log(Status.PASS, "Delay input is disabled after start all live");
		Assert.assertTrue(homePage.isAllChStarted(),"Some channels are not started after click start all");
		node.log(Status.PASS, "All channels are started after click start all");
		
	}
	
	@Test
	public void stopAllLiveTest() {
		homePage.clkStopAllBtn();
		homePage.sleepTime(1000);
		Assert.assertTrue(homePage.isStopAll(), "Stop All button doesn't change to Start All button");
		node.log(Status.PASS, "Stop All button change to Start All button");
		Assert.assertFalse(homePage.isDelayInputDisabled(), "Delay input is enabled after stop All live");
		node.log(Status.PASS, "Delay input is enabled after stop all live");
		Assert.assertTrue(homePage.isAllChStopped(),"Some channels are not stopped after stop all live");
		node.log(Status.PASS, "All channels are stopped after stop all live");
	}
	
	@Test
	public void setDelayTest() {
		String delay = "10";
		homePage.fillDelay(delay);
		homePage.clkDelayApplyBtn();
		Assert.assertEquals(homePage.getDelay(), delay,"Fail to set delay");
		node.log(Status.PASS, "Success to set delay");
		homePage.refreshPage();
		Assert.assertEquals(homePage.getDelay(), delay,"Delay changes after refresh page");
		node.log(Status.PASS, "Delay is correct after refresh page");
	}

	@Override
	public void closeObject() {
		homePage = null;
	}
	

}
