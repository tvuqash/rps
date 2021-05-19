package com.tvu.shqa.rpspage.page.encoderpage;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.Status;
import com.tvu.shqa.rpspage.page.PageBaseClass;
import com.tvu.shqa.rpspage.test.TestBaseClass;

public class HomePage extends PageBaseClass {
	
	private final static String PAGE_TITLE = "TVU RPS";

	public HomePage(WebDriver _driver) {
		super(_driver);
	}
	
	@FindBy(css = ".operationSection>.liveBtn>button")
	List<WebElement> chLiveBtns;
	
	@FindBy(css = ".mainBtnStart")
	WebElement startAllBtn;
	
	@FindBy(css = ".border.latency")
	WebElement delayInput;
	
	@FindBy(css = ".setDelayBtn")
	WebElement delayApplyBtn;
	
	public String getPageTitle() {
		String pageTitle = driver.getTitle();
		TestBaseClass.node.log(Status.INFO, "Get page title:" + pageTitle);
		return pageTitle;
	}
	
	public boolean isInHomePage() {
		//return PAGE_TITLE.equals(getPageTitle());
		try {
			return webDriverWait(driver,2).until(ExpectedConditions.titleIs(PAGE_TITLE));
		}catch (Exception e) {
			return false;
		}
	}
	
	public Alert getAlert() {
		try {
			return webDriverWait(driver,3).until(ExpectedConditions.alertIsPresent());
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean isStartAll() {
		return "Stop All".equals(getText(startAllBtn)) && startAllBtn.getAttribute("class").contains("bg-red");
	}
	
	public boolean isStopAll() {
		return "Start All".equals(getText(startAllBtn));
	}
	
	
	public void clkStartAllBtn() {
		moveToEle(driver,startAllBtn);
		if(isStartAll()) {
			TestBaseClass.node.log(Status.WARNING,"It have started All live.Stop All");
			clkEle(startAllBtn);
		}
		TestBaseClass.node.log(Status.INFO,"Click Start All button");
		clkEle(startAllBtn);
	}
	
	public void clkStopAllBtn() {
		moveToEle(driver,startAllBtn);
		if(isStopAll()) {
			TestBaseClass.node.log(Status.WARNING,"It have not started All live.Start All");
			clkEle(startAllBtn);
		}
		
		TestBaseClass.node.log(Status.INFO,"Click Stop All button");
		clkEle(startAllBtn);
	}
	
	public boolean isAllChStarted() {
		for(WebElement ele : chLiveBtns) {
			//scrollToEleByJS(driver,ele);
			moveToEle(driver,ele);
			if(! "Stop".equals(getText(ele))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void stopCh(WebElement chEle) {
		moveToEle(driver,chEle);
		if(!"Start".equals(getText(chEle))) {
			TestBaseClass.node.log(Status.INFO,"Stop channel");
			clkEle(chEle);
		}
		
		TestBaseClass.node.log(Status.INFO,"Channel is not started,not have to stop");
	}
	
	public void startCh(WebElement chEle) {
		moveToEle(driver,chEle);
		if(!"Stop".equals(getText(chEle))) {
			TestBaseClass.node.log(Status.INFO,"Start channel");
			clkEle(chEle);
		}
		
		TestBaseClass.node.log(Status.INFO,"Channel has been started,not have to start");
	}
	
	public void stopCh(int chIndex) {
		WebElement btn = chLiveBtns.get(chIndex - 1);
		moveToEle(driver,btn);
		if(!"Start".equals(getText(btn))) {
			TestBaseClass.node.log(Status.INFO,"Stop channel:" + chIndex);
			clkEle(btn);
		}
		
		TestBaseClass.node.log(Status.INFO,"Channel:" + chIndex + " is not started,not have to stop");
	}
	
	public void startCh(int chIndex) {
		WebElement btn = chLiveBtns.get(chIndex - 1);
		moveToEle(driver,btn);
		if(!"Stop".equals(getText(btn))) {
			TestBaseClass.node.log(Status.INFO,"Start channel:" + chIndex);
			clkEle(btn);
		}
		
		TestBaseClass.node.log(Status.INFO,"Channel:" + chIndex + " has been started,not have to start");
	}
	
	
	
	public void stopAllChs() {
		for(int i = 1;i <= chLiveBtns.size();i++) {
			stopCh(i);
		}
	}
	
	public void startAllChs() {
		for(int i = 1;i <= chLiveBtns.size();i++) {
			startCh(i);
		}
	}
	
	public boolean isAllChStopped() {
		for(WebElement ele : chLiveBtns) {
			//scrollToEleByJS(driver,ele);
			moveToEle(driver,ele);
			if(! "Start".equals(getText(ele))) {
				return false;
			}
		}
		
		return true;
	}
	
	public void refreshPage() {
		TestBaseClass.node.log(Status.INFO,"Refresh Home Page");
		driver.navigate().refresh();
		closeWebPreviewAlert();
	}
	
	public void closeWebPreviewAlert() {
		Alert alert = getAlert();
		if( alert != null) {
			alert.accept();
		}
	}
	
	public boolean isDelayInputDisabled() {
		return delayInput.getAttribute("class").contains("border-gray");
	}
	
	public boolean isDelayApplyBtnDisabled() {
		return delayApplyBtn.getAttribute("class").contains("border-gray");
	}
	
	public void fillDelay(String delay) {
		moveToEle(driver,delayInput);
		if(isDelayInputDisabled()) {
			TestBaseClass.node.log(Status.INFO,"Delay input is disabled,stop all channels to enable it");
			stopAllChs();
		}
		
		TestBaseClass.node.log(Status.INFO,"Input delay:" + delay);
		//sendKeys(delayInput,delay,true);
		sendKeysByActions(driver,delayInput,delay);
	}
	
	public void clkDelayApplyBtn() {
		moveToEle(driver,delayApplyBtn);
		TestBaseClass.node.log(Status.INFO,"Click delay apply button");
		clkEle(delayApplyBtn);
	}
	
	public String getDelay() {
		String delay = getEleValueByJS(driver,delayInput);
		TestBaseClass.node.log(Status.INFO,"Get delay:" + delay);
		return delay;
	}
	
	
	
	

}
