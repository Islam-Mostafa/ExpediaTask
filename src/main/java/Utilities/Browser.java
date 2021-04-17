package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {
	private WebDriver webdriver;
	public static final String IE = "IE";
	public static final String Chrome = "Chrome";
	public static final String FireFox = "FireFox";
	public WebDriver getDriver(String name) {

		switch (name) {

		case Browser.IE:
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setBrowserName("internet explorer");
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			caps.setCapability("ignoreZoomSetting", true);
			caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
			System.setProperty("webdriver.ie.driver", "src/test/resources/seleniumdrivers/IEDriverServer.exe");
			webdriver = new InternetExplorerDriver();
			break;

		case Browser.Chrome:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/seleniumdrivers/chromedriver.exe");
			webdriver = new ChromeDriver();
			break;
			
		case Browser.FireFox:
			System.setProperty("webdriver.gecko.driver","src/test/resources/seleniumdrivers/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			break;
			
		}
		return webdriver;

	}
}

