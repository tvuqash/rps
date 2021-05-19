package com.tvu.shqa.rpspage.utils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	ExtentSparkReporter htmlReport;
	public static ExtentReports report;
	public static ExtentTest suite;
	public static ExtentTest test;
	public static ExtentTest node;
	
	public static void setReport() {
		report = new ExtentReports();
		ExtentSparkReporter htmlReport = new ExtentSparkReporter("build/Report.html");
		report.attachReporter(htmlReport);
		report.setAnalysisStrategy(AnalysisStrategy.SUITE);
	}
	
	public static void flushReport() {
		report.flush();
	}
	
	public static void setSuite(String suiteName) {
		suite = report.createTest(suiteName);
	}
	
	public static void setTest(String testName) {
		test = suite.createNode(testName);
	}
	
	public static void setNode(String nodeName) {
		node = test.createNode(nodeName);
	}

}
