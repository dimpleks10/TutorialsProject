package com.me.TutorialsProject.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	//objects
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	 
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
		
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public AccountPage clickLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	

	public String actualEmailPasswordNotMatchingWarning() {
		String actaulWarningText = emailPasswordNotMatchingWarning.getText();
		return actaulWarningText;
		
	}
	

}
