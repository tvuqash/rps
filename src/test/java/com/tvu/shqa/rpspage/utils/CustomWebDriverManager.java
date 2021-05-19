package com.tvu.shqa.rpspage.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriverManager {
	public WebDriver driver;
	public String driverType;
	public boolean inincognito;
	
	public CustomWebDriverManager(String driverType,boolean inincognito) {
		this.driverType = driverType;
		this.inincognito = inincognito;
		initDriver();
	}
	
	public void initDriver() {
		if("chrome".equalsIgnoreCase(driverType)) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(inincognito) {
				options.addArguments("-incognito");
			}
			
			driver = new ChromeDriver(options);
		}else if("firefox".equalsIgnoreCase(driverType)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if("ie".equalsIgnoreCase(driverType)) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
	public void quitDriver(WebDriver _driver) {
		_driver.quit();
	}
	

}
