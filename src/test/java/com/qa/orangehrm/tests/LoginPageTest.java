package com.qa.orangehrm.tests;

import static org.testng.Assert.fail;

import java.util.Properties;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.page.HomePage;
import com.qa.orangehrm.page.LoginPage;
import com.qa.orangehrm.util.AppConstant;
import com.qa.orangehrm.util.Credentials;



public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeMethod
	public void setUp()  {
	
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1, description="verify page title method", enabled=true)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getPageTitle();
		System.out.println("login page title is "+ title);
		Assert.assertEquals(title, AppConstant.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2, description="verify sign up link is displayed", enabled=true)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkForgetPassLink());
	}
	
	@Test(priority=3, description="login system with valid username and password", enabled=true)
	public void loginTest() {
		HomePage homePage = loginPage.doLogin(userCred);
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account is "+ accountName);
		Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
	}
	
	@DataProvider
	public Object[][] getLoginInvalidData(){
		Object data[][] = {{"Admin", " "},
				           {"admin", "12345"},
				           {"1234", "admin"}
			
		};
		return data;
	}
	
	@Test(priority=4, dataProvider= "getLoginInvalidData", enabled=false)
	public void login_Invalid_Creds(String username, String pwd) {
		userCred.setAppPassword(username);
		userCred.setAppPassword(pwd);
		loginPage.doLogin(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
