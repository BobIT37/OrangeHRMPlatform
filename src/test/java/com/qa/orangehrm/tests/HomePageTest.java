package com.qa.orangehrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.page.HomePage;
import com.qa.orangehrm.page.LoginPage;
import com.qa.orangehrm.util.AppConstant;
import com.qa.orangehrm.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver = basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
	}
	
	@Test(priority=1, description="verify home page title")
	public void verifyHomePageTitle() {
		String title = homePage.getHomePagetitle();
		System.out.println("home page title is "+ title);
		Assert.assertEquals(title, AppConstant.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2, description="verify home page header")
	public void verifyHomePageHeader() {
		String header = homePage.getHomePageHeader();
		System.out.println("home page header is "+ header);
		Assert.assertEquals(header, AppConstant.HOME_PAGE_HEADER);
	}
	
	@Test(priority=3, description="verify account name method")
	public void verifyLoggedInUserTest() {
		String accountName = homePage.getLoggedInUserAccountName();
		System.out.println("logged in account is "+ accountName);
		//Assert.assertEquals(accountName, prop.getProperty("accountname"));
		Assert.assertTrue(accountName.contains(prop.getProperty("accountname")));
	}
	
	@Test(priority=4, description="verify page URL")
	public void verifyAuthentication() {
		String url = homePage.getHomeUrl();
		System.out.println("page url is "+ url);
		Assert.assertTrue(url.endsWith("https://opensource-demo.orangehrmlive.com/index.php/dashboard"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
