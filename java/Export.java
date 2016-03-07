package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class Export {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://github.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testExport_back() throws Exception {
		selenium.open("/");
		selenium.waitForPageToLoad("100000");
		selenium.type("name=q", "marcela duarte");
		selenium.keyPress("name=q", "\\13");
		selenium.waitForPageToLoad("30000");
		for (int second = 0;; second++) {
			if (second >= 60){
				 fail("timeout");
			}
			try {
				if ("Users1".equals(selenium.getText("link=Users1"))){
					 break;
				}
			} catch (Exception e) {
				Thread.sleep(1000);
			}
		}

		selenium.click("link=Users1");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try {
				if ("marceladuarte".equals(selenium.getText("css=em"))){
					 break;
				}
			} catch (Exception e) {}
			Thread.sleep(1000);
		}

		assertEquals("marceladuarte", selenium.getText("css=em"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
