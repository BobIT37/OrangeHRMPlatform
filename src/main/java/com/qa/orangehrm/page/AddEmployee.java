package com.qa.orangehrm.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.orangehrm.util.ElementUtil;

public class AddEmployee {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By clickPIM = By.xpath("//b[contains(text(),'PIM')]");
	By addEmployee = By.cssSelector("#menu_pim_addEmployee");
	By firstName = By.id("firstName");
	By lastName = By.id("lastName");
	By btnSave = By.id("btnSave");
	By pageHeader = By.xpath("//h1[contains(text(),'Add Employee')]");
	
	public AddEmployee(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getAddEmployeePageHeader() {
		String header = elementUtil.getElement(pageHeader).getText();
		System.out.println("Header is "+ header);
		return header;
	}
	
	public void doClick() {
		elementUtil.doClick(clickPIM);
		elementUtil.doClick(addEmployee);
	}
	
	public void addEmployee(String fName, String lName) {
//		elementUtil.doClick(clickPIM);
//		elementUtil.doClick(addEmployee);
		elementUtil.doSendKeys(firstName, fName);
		elementUtil.doSendKeys(lastName, lName);
		elementUtil.doClick(btnSave);
	}
	
	
	
	
	

}
