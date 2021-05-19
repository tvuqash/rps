package com.tvu.shqa.rpspage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.tvu.shqa.rpspage.utils.PageOperation;

public class PageBaseClass extends PageOperation{
	protected WebDriver driver;
	public PageBaseClass(WebDriver _driver) {
		driver = _driver;
		PageFactory.initElements(driver, this);
	}
}
