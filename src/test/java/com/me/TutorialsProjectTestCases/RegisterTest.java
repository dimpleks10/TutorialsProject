package com.me.TutorialsProjectTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.me.TutorialsProject.base.Base;
import com.me.TutorialsProject.pom.AccountSuccessPage;
import com.me.TutorialsProject.pom.HomePage;
import com.me.TutorialsProject.pom.RegisterPage;
import com.me.TutorialsProject.utils.Utilities;

public class RegisterTest extends Base {
	
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setUp() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage= homePage.selectRegisterOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 0)
	public void verifyRegisterAnAccountWithMandatoryFields() {
		
		
		registerPage.enterFirstName(dataProp.getProperty("firtsName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genrateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyCheckBox();
		accountSuccessPage=registerPage.selectContinueButton();
		

		String actualSuccessMsg = accountSuccessPage.actualAccountCreationSuccessHeadingText();

		Assert.assertEquals(actualSuccessMsg, dataProp.getProperty("accountSuccessfulCreatedHeading"),
				"Account creation success msg is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisterAnAccountWithAllFields() {

		registerPage.enterFirstName(dataProp.getProperty("firtsName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.genrateEmailWithTimeStamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.subscribeNewLetter();
		registerPage.selectPrivacyCheckBox();
		accountSuccessPage=registerPage.selectContinueButton();

		String actualSuccessMsg = accountSuccessPage.actualAccountCreationSuccessHeadingText();
		Assert.assertEquals(actualSuccessMsg, dataProp.getProperty("accountSuccessfulCreatedHeading"),
				"Account creation success msg is not displayed");

	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountWithExistingEmail() {
		
		registerPage.enterFirstName(dataProp.getProperty("firtsName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.confirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyCheckBox();
		accountSuccessPage=registerPage.selectContinueButton();

		String actualWarningMsg = registerPage.getDuplicateEmailWarningText();
		Assert.assertEquals(actualWarningMsg, dataProp.getProperty("duplicateEmailWarning"),
				"Warning did not displayed");

	}

	@Test(priority = 1)
	public void verifyRegisterAnAccountWithoutFillingAnyFields() {
		
		registerPage.selectContinueButton();

		String actualPrivacyPolicyWarning = registerPage.getPrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),
				"No Privacy policy warning message is displayed");

		String actualFirstNameWarning = registerPage.getFirstNameWarningText();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),
				"First Name warning mismatch");

		String actualLastNameWarning = registerPage.getLastNameWarningText();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),
				"Last Name warning mismatch");

		String actualEmailWarning = registerPage.getEmailWarningText();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"), "Email warning mismatch");

		String actualTelephoneWarning = registerPage.getTelephoneWarningText();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneNumWarning"),
				"Telephone warning mismatch");

		String actualPasswordWarning = registerPage.getPasswordWarningText();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),
				"Password warning mismatch");

	}

}
