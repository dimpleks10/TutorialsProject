package com.me.TutorialsProject.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="HP LP3065")
	private WebElement validProductText;
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	private WebElement noSuchProductText;
	
	public boolean getValidProductText() {
		boolean actualValidProductText = validProductText.isDisplayed();
		return actualValidProductText;
	}

	public String getNoSuchProductText() {
		String actualNoSuchProductText=noSuchProductText.getText();
		return actualNoSuchProductText;
	}
}
