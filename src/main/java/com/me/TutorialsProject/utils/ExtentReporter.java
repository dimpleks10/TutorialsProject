package com.me.TutorialsProject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		
	ExtentReports extentReport = new ExtentReports();
	File extentReporterFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReporter\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReporterFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tutorials Project Automation Test Results");
	sparkReporter.config().setDocumentTitle("TP Automation Results");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	
	Properties configProps = new Properties();
	
	File configPropsFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\me\\TutorialsProject\\config\\config.properties");
	
	try {
	FileInputStream fisCP = new FileInputStream(configPropsFile);
	configProps.load(fisCP);
	}catch(Throwable e){
		e.getStackTrace();
	}
	
	
	
	extentReport.setSystemInfo("Application URL",configProps.getProperty("url"));
	extentReport.setSystemInfo("Browser Name", configProps.getProperty("browser"));
	extentReport.setSystemInfo("Email", configProps.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configProps.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System ", System.getProperty("os.name"));
	extentReport.setSystemInfo("User Name ", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	
	return extentReport;

	}
}
