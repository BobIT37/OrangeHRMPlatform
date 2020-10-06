package com.qa.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.Credentials;
import com.qa.orangehrm.util.ElementUtil;



public class LoginPage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By username = By.id("txtUsername");
	By password = By.id("txtPassword");
	By loginBtn = By.id("btnLogin");
	By forgetPass = By.xpath("//a[contains(text(),'Forgot your password?')]");
	By loginErrorText = By.id("spanMessage");
			
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver); //2
	}
	
	//Methods
	
	public String getPageTitle() {
		return elementUtil.doGetPageTitle();
	}
	
	public boolean checkForgetPassLink() {
		return elementUtil.doIsDisplayed(forgetPass);
	}
	
    public HomePage doLogin(Credentials userCred) {
		elementUtil.waitForElementPresent(username);
		elementUtil.doSendKeys(username, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginBtn);

		
		return new HomePage(driver);
	}
	
	public boolean checkLoginErrorMessage() {
		return elementUtil.doIsDisplayed(loginErrorText);
	}

}
