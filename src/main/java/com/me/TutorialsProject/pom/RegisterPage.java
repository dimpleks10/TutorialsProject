package com.me.TutorialsProject.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	
	WebDriver driver;

	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneNumberField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@value='1' and @name='newsletter']")
	private WebElement newsLetter;
	
	@FindBy(name="agree")
	private WebElement policyCheckBox;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//actions
	
	public void enterFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void enterEmailAddress(String email) {
		emailAddressField.sendKeys(email);
	}
	
	public void enterTelephoneNumber(String telephoneNumber) {
		telephoneNumberField.sendKeys(telephoneNumber);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void confirmPassword(String password) {
		confirmPasswordField.sendKeys(password);
	}
	
	public void subscribeNewLetter() {
		newsLetter.click();
	}
	
	public void selectPrivacyCheckBox() {
		policyCheckBox.click();
	}
	
	
	public AccountSuccessPage selectContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	
	public String getDuplicateEmailWarningText() {
		String actualDuplicateEmailText=duplicateEmailWarning.getText();
		return actualDuplicateEmailText;
	}
	
	public String getPrivacyPolicyWarning() {
		String actualPrivacyPolicyWarningText=privacyPolicyWarning.getText();
		return actualPrivacyPolicyWarningText;
	}
	
	public String getFirstNameWarningText() {
		String actualFirstNameWarningText=firstNameWarning.getText();
		return actualFirstNameWarningText;
	}
	
	public String getLastNameWarningText() {
		String actualLastNameWarningString=lastNameWarning.getText();
		return actualLastNameWarningString;
	}
	
	public String getEmailWarningText() {
		String actualEmailWarningText = emailWarning.getText();
		return actualEmailWarningText;
	}
	
	public String getTelephoneWarningText() {
		String actualTelephoneWarningText = telephoneWarning.getText();
		return actualTelephoneWarningText;
	}
	
	public String getPasswordWarningText() {
		String actualPasswordWarningText=passwordWarning.getText();
		return actualPasswordWarningText;
	}
}
