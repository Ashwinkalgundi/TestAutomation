package seleniumautomation;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class LoginToAmazon {

	FirstTestScript firstTestScript = new FirstTestScript();

	@BeforeSuite
	public void beforeClass() {
		try {
			firstTestScript.beforeClass();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void afterClass() {
		firstTestScript.afterClass();
	}

	@Test
	public void testLinks() {
		firstTestScript.openBrowser();
		List<WebElement> elementList = new ArrayList();
		elementList = firstTestScript.driver.findElements(By.tagName("a"));
		System.out.println("All links found on web page are: " + elementList.size() + "links");
		for (WebElement links : elementList) {
			System.out.println(links.getText());
			System.out.println(links.getAttribute("href"));
		}
	}
}
