package com.tvu.shqa.rpspage.page.encoderpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;
import com.tvu.shqa.rpspage.page.PageBaseClass;
import com.tvu.shqa.rpspage.test.TestBaseClass;

public class LoginPage extends PageBaseClass  {
	private final static String PAGE_NAME = "TVU RPS";
	
	public LoginPage(WebDriver _driver) {
		super(_driver);
	}
	
	@FindBy(css = ".title")
	WebElement pageNameEle;
	
	@FindBy(id = "username")
	WebElement usernameInput;
	
	@FindBy(id = "password")
	WebElement passwordInput;
	
	@FindBy(css = ".u-btn-green")
	WebElement loginBtn;
	
	
	
	
	public String getPageName() {
		String pageName = getText(pageNameEle);
		TestBaseClass.node.log(Status.INFO, "Get page name:" + pageName);
		return pageName;
	}
	
	public boolean isInLoginPage() {
		return PAGE_NAME.equals(getPageName());
	}
	
	public void fillUsername(String username) {
		TestBaseClass.node.log(Status.INFO,"Fill username:" + username);
		sendKeys(usernameInput,username,true);
	}
	
	public void fillPassword(String password) {
		TestBaseClass.node.log(Status.INFO,"Fill username:" + password);
		sendKeys(passwordInput,password,true);
	}
	
	public void clkLoginBtn() {
		TestBaseClass.node.log(Status.INFO,"Click login button");
		clkEle(loginBtn);
	}
	
	public void login(String username,String password) {
		fillUsername(username);
		fillPassword(password);
		clkLoginBtn();
	}
	
	

}
