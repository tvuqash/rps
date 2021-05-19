package com.tvu.shqa.rpspage.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageOperation {
	
	public boolean isDisplayed(WebElement ele) {
		try {
			return ele.isDisplayed();
		}catch (Exception e) {
			return false;
		}
	}
	
	public void moveToEle(WebDriver _driver,WebElement ele) {
		new Actions(_driver).moveToElement(ele).perform();
	}
	
	public void sendKeys(WebElement ele,String keys,boolean isClear) {
		if(isClear) {
			ele.clear();
		}
		ele.sendKeys(keys);
	}
	
	public void sendKeys(WebElement ele,Keys key,boolean isClear) {
		if(isClear) {
			ele.clear();
		}
		ele.sendKeys(key);
	}
	
	public void clkEle(WebElement ele) {
		ele.click();
		sleepTime(300);
	}
	
	public void sleepTime(int mseconds) {
		try {
			Thread.sleep(mseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText(WebElement ele) {
		return ele.getText();
	}
	
	public WebDriverWait webDriverWait(WebDriver _driver,int seconds) {
		return new WebDriverWait(_driver,seconds);
	}
	
	public WebElement elementVisibility(WebDriver _driver,int seconds,WebElement element) {
		return webDriverWait(_driver,seconds).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToFrame(WebDriver _driver,int index) {
		_driver.switchTo().frame(index);
	}
	
	public boolean elementNotVisibility(WebDriver _driver,int seconds,WebElement element) {
		return webDriverWait(_driver,seconds).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
	}
	
	public void scrollToEleByJS(WebDriver _driver,WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)_driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);
		sleepTime(300);
	}
	
	public String getEleValueByJS(WebDriver _driver,WebElement ele) {
		JavascriptExecutor js=(JavascriptExecutor)_driver;
		return (String)js.executeScript("return arguments[0].value", ele);
	}
	
	public void sendKeysByActions(WebDriver _driver,WebElement ele,String key) {
		new Actions(_driver).sendKeys(ele, Keys.CONTROL + "A").sendKeys(ele,Keys.BACK_SPACE).sendKeys(ele, key).build().perform();
	}
	
	


}
