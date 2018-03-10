package seleniumautomation;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import seleniumautomation.ConfigurationReader;

public class FirstTestScript {

	public static WebDriver driver;

	private static Logger logger = Logger.getLogger("FirstTest");

	@BeforeClass
	public void beforeClass() throws Throwable {
		//System.setProperty("webdriver.gecko.driver", "C:/Test Automation/geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ ConfigurationReader.getValue("WebDriverfirefoxDriverPath"));
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ ConfigurationReader.getValue("WebDriverfirefoxDriverPath"));
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void openBrowser() {
		logger.info(FirstTestScript.class);
		driver.get("https://www.amazon.in/");
		WebElement WebElement = driver.findElement(By.xpath("//div/a/span[text()='Amazon']"));
		WebElement.isDisplayed();
	}

	@Test(dependsOnMethods = { "openBrowser" })
	public void searchBooks() {
		logger.info(FirstTestScript.class);
		WebElement searchBox = driver.findElement(By.cssSelector("div input[id='twotabsearchtextbox']"));
		searchBox.clear();
		searchBox.sendKeys("data structure and algorithm made easy");
		driver.findElement(By.cssSelector("input[value='Go']")).click();
		WebElement explicitWaitElement = (new WebDriverWait(driver, 10)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='a-color-state a-text-bold']")));
		logger.info("searchBooks is successfully");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
