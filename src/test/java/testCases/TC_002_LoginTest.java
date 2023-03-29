package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass{

	@Test(groups= {"Sanity", "Regression"})
	public void login() {
		try {
			logger.info(" ****       started TC_002_LoginTest           ****");
			HomePage hp = new HomePage(webDriver);
			hp.clickMyAccount();
			hp.clickLogin();

			AccountLoginPage login = new AccountLoginPage(webDriver);
			login.setEmail(rs.getString("email"));
			login.setPassword(rs.getString("password"));
			login.clickLogin();

			MyAccountPage accPage = new MyAccountPage(webDriver);
			Assert.assertTrue(accPage.isMyAccountPageExists());
			Thread.sleep(3000);
			logger.info(" ****       completed TC_002_LoginTest           ****");
		} catch(Exception e) {
			logger.error("TC_002_LoginTest failed");
			Assert.fail();
		}

	}

}
