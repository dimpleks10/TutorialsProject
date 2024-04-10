package com.me.TutorialsProject.listeneres;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.me.TutorialsProject.utils.ExtentReporter;
import com.me.TutorialsProject.utils.Utilities;

public class MyListeneres implements ITestListener{

	ExtentReports extentReport ;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		
		//System.out.println("Execution of the project tests started");
		extentReport = ExtentReporter.generateExtentReport();
		
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() +" : stated executing ");
		//System.out.println(testName+" : stated executing ");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, result.getName() +" : got passed ");
		//System.out.println(testName+" : got passed ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		WebDriver driver=null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath =Utilities.captureScreenshot(driver, result.getName());
		//to embed ss to report
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.FAIL, result.getName()+" : got failed ");
		extentTest.log(Status.INFO, result.getThrowable());
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		//extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.SKIP, result.getName() +  " : got skipped ");
		extentTest.log(Status.INFO, result.getThrowable());
		
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		//System.out.println("Execution of the project tests Finished!");
		
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReporter\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	

}
