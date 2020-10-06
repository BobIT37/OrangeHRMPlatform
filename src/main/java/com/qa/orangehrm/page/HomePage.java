package com.qa.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.ElementUtil;

public class HomePage {
	
	WebDriver driver;
	ElementUtil elementUtil; //1
	//Locator
	
	By accountName = By.id("welcome");
	By header = By.xpath("//h1[contains(text(),'Dashboard')]");
	
	//Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	//methods
    public String getHomePagetitle() {
    	return elementUtil.doGetPageTitle();
    }
    
    public String getHomePageHeader() {
    	return elementUtil.doGetText(header);
    }
    
    public String getHomeUrl() {
    	return elementUtil.doGetPageUrl();
    }
    
    public String getLoggedInUserAccountName() {
    	return elementUtil.doGetText(accountName);
    }

}
