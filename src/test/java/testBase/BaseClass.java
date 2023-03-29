package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	protected static WebDriver webDriver;
	
	public Logger logger;
	
	public ResourceBundle rs;
	
	@BeforeClass(groups= {"Sanity", "Regression"})
	@Parameters("browser")
	public void setUp(String browser) throws InterruptedException {
		
		logger = LogManager.getLogger(this.getClass());
		rs = ResourceBundle.getBundle("config");
		
		if("chrome".equals(browser)) {
			webDriver = new ChromeDriver();
		} else if("edge".equals(browser)) {
			webDriver = new EdgeDriver();
		} else {
			webDriver = new FirefoxDriver();
		}
		
		
		webDriver.get(rs.getString("appURL"));
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		webDriver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression"})
	public void tearDown() {
		webDriver.quit();
	}

	public String getRandomString() {
		return RandomStringUtils.randomAlphabetic(4);
	}
	
	public String getRandomNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String generateRandomPassword() {
		CharacterRule lowerCaseAlpha = new CharacterRule(EnglishCharacterData.LowerCase);
		lowerCaseAlpha.setNumberOfCharacters(5);
		CharacterRule upperCaseAlpha = new CharacterRule(EnglishCharacterData.UpperCase);
		upperCaseAlpha.setNumberOfCharacters(1);
		CharacterRule digit = new CharacterRule(EnglishCharacterData.Digit);
		digit.setNumberOfCharacters(1);
		CharacterRule specialChar = new CharacterRule(EnglishCharacterData.Special);
		specialChar.setNumberOfCharacters(1);
		PasswordGenerator password = new PasswordGenerator();
		return password.generatePassword(8, lowerCaseAlpha, upperCaseAlpha, digit, specialChar);
	}

	public String captureScreen(String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) webDriver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		FileUtils.copyFile(src, new File(dest));
		return dest;
	}

}
