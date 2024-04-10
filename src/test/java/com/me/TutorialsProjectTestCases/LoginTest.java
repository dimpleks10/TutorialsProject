package com.me.TutorialsProjectTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.me.TutorialsProject.base.Base;
import com.me.TutorialsProject.pom.AccountPage;
import com.me.TutorialsProject.pom.HomePage;
import com.me.TutorialsProject.pom.LoginPage;
import com.me.TutorialsProject.utils.Utilities;

public class LoginTest extends Base {
	
	LoginPage loginPage; 

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 0, dataProvider="validCredentials")
	public void verifyLoginWithValidCreadentials(String email, String password) {
		
		
		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickLoginButton();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationIsDisplayed(),
				"Not in home page");
	}
	
	@DataProvider(name="validCredentials")
	public Object[][] supplyTestData() {
		Object[][] data = 
				//Utilities.getTestDataFromExcel("Login");
			{{"dimprad.21@gmail.com","9876"},{"dimpleks10@gmail.com","12345"},{"dimpleks897@gmail.com","12345"}} ;
		
		return data;
	}

	@Test(priority = 1)
	public void verifyLoginWithInalidCreadentials() {

		
		loginPage.enterEmailAddress(Utilities.genrateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invaildPassword"));
		loginPage.clickLoginButton();
		
		String actualWarning = loginPage.actualEmailPasswordNotMatchingWarning();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInalidEmailAndValidPassword() {

		loginPage.enterEmailAddress(Utilities.genrateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickLoginButton();
		
		String actualWarning = loginPage.actualEmailPasswordNotMatchingWarning();
		String expectedWarning = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invaildPassword"));
		loginPage.clickLoginButton();

		String actualWarning =loginPage.actualEmailPasswordNotMatchingWarning();
		String expectedWarning = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithoutEmailAndPassword() {
		
		loginPage.clickLoginButton();
		
		String actualWarning =loginPage.actualEmailPasswordNotMatchingWarning();
		String expectedWarning = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message not displayed");

	}

}
