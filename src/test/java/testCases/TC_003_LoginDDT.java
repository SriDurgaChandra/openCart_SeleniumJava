package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void loginTest(String email, String password, String exp) {
	try {
		logger.info(" ****       started TC_003_LoginTest           ****");
		HomePage hp = new HomePage(webDriver);
		hp.clickMyAccount();
		hp.clickLogin();

		AccountLoginPage login = new AccountLoginPage(webDriver);
		login.setEmail(email);
		login.setPassword(password);
		login.clickLogin();

		MyAccountPage accPage = new MyAccountPage(webDriver);
		boolean isLoggedIn = accPage.isMyAccountPageExists();
		
		if("Valid".equals(exp) && isLoggedIn) {
			accPage.clickLogOut();
			Assert.assertTrue(true);
		} else if("Invalid".equals(exp) && isLoggedIn) {
			accPage.clickLogOut();
			Assert.assertTrue(false);
		} else if("Valid".equals(exp) && !isLoggedIn) {
			//accPage.clickLogOut();
			Assert.assertTrue(false);
		} else if("Invalid".equals(exp) && !isLoggedIn) {
			//accPage.clickLogOut();
			Assert.assertTrue(true);
		}
		
		logger.info(" ****       completed TC_003_LoginTest           ****");
	} catch(Exception e) {
		logger.error("TC_003_LoginTest failed");
		Assert.fail();
	}
}
}
