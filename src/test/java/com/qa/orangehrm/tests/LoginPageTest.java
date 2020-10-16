package com.qa.orangehrm.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.orangehrm.base.BasePage;
import com.qa.orangehrm.page.HomePage;
import com.qa.orangehrm.page.LoginPage;
import com.qa.orangehrm.util.AppConstant;
import com.qa.orangehrm.util.Credentials;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Epic("Epic - 101 : create login features")
@Feature("US - 501 : create test for login on Orange HRM")
public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	Logger log = Logger.getLogger(LoginPageTest.class);
	
	@BeforeMethod
	@Parameters(value= {"browser"}) //
	public void setUp(String browser) {//
	    String browserName = null; //
		basePage = new BasePage();
		prop = basePage.init_properties();
		
		if(browser.equals(null)) { //
			browserName = prop.getProperty("browser");//
		}else {//
			browserName = browser;//
		}
		
		driver = basePage.init_driver(browserName);
		log.info("browser is laucnhing-----------------> setUp method");
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test(priority=1, description="verify page title method", enabled=true)
	@Description("verify login page title")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest() {
		log.info("starting --------------------------> verify login page title");
		String title = loginPage.getPageTitle();
		System.out.println("login page title is "+ title);
		Assert.assertEquals(title, AppConstant.LOGIN_PAGE_TITLE);
		log.info("endging ----------------------------> verify login page title");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal eror");
	}
	
	@Test(priority=2, description="verify sign up link is displayed", enabled=true)
	@Description("verify sign up link")
	@Severity(SeverityLevel.NORMAL)
	public void verifySignUpLink() {
		Assert.assertTrue(loginPage.checkForgetPassLink());
	}
	
	@Test(priority=3, description="login system with valid username and password", enabled=true)
	@Description("verify login system")
	@Severity(SeverityLevel.CRITICAL)
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
