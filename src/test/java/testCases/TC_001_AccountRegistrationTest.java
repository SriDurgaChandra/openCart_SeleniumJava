package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups= {"Sanity"})
	private void accountRegisterationTest() throws InterruptedException {
		try
		{
			logger.debug("Application logs.....");
			logger.info(" ****   Starting TC_001   **** ");
			HomePage hp = new HomePage(webDriver);
			hp.clickMyAccount();
			hp.clickRegister();
			logger.info("Clicked on Register");
			AccountRegistrationPage regpage=new AccountRegistrationPage(webDriver);
			logger.info("Providing customer details");
			regpage.setFirstName(getRandomString().toUpperCase());

			regpage.setLastName(getRandomString().toUpperCase());

			regpage.setEmail(getRandomString()+"@gmail.com");// randomly generated the email

			regpage.setTelephone(getRandomNumber());
			
			String password = generateRandomPassword();
			
			regpage.setPassword(password);
			
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();

			regpage.clickContinue();
			logger.info("Clicked on Continue");

			String confmsg=regpage.getConfirmationMsg();
			logger.info("Validating expected msg");
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Expected msg is unavailable");
			
			logger.info(" ****   Finished TC_001   **** ");
		}
		catch(Exception e)
		{
			logger.error("TC_001 failed");
			Assert.fail();
		}
	}

}
