package com.me.TutorialsProject.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.me.TutorialsProject.utils.Utilities;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	public  Base() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\me\\TutorialsProject\\config\\config.properties");
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e ) {
			e.printStackTrace();
			
		}
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\me\\TutorialsProject\\testData\\testdata.properties");
		try {
		FileInputStream fisData= new FileInputStream(dataPropFile);
		dataProp.load(fisData);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
