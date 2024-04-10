package com.me.TutorialsProjectTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.me.TutorialsProject.base.Base;
import com.me.TutorialsProject.pom.HomePage;
import com.me.TutorialsProject.pom.SearchPage;

public class SearchTest extends Base{
	
	SearchPage searchPage;
	
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		
		HomePage homePage= new HomePage(driver);
		homePage.sendSearchTextToSeachBox(dataProp.getProperty("vaildProduct"));
		searchPage=homePage.clickOnSearchButton();
		
		Assert.assertTrue(searchPage.getValidProductText(),"searched product mismatch");
	}

	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		HomePage homePage= new HomePage(driver);
		homePage.sendSearchTextToSeachBox(dataProp.getProperty("inValidProduct"));
		searchPage=homePage.clickOnSearchButton();
		
		String actualNoSuchProductMsg=searchPage.getNoSuchProductText();
		Assert.assertEquals(actualNoSuchProductMsg,dataProp.getProperty("noProductText"),"No product message is not displayed");

	}
	
	@Test(priority=3,dependsOnMethods= {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage= new HomePage(driver);
		searchPage=homePage.clickOnSearchButton();
			
		String actualNoSuchProductMsg=searchPage.getNoSuchProductText();
		Assert.assertEquals(actualNoSuchProductMsg,dataProp.getProperty("noProductText"),"No product message is not displayed without any product");
	}
}

