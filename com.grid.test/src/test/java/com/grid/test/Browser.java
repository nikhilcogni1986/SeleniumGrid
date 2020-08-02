package com.grid.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browser {

	public static RemoteWebDriver driver;

	public static RemoteWebDriver getDriver(String browser) throws MalformedURLException {
		return new RemoteWebDriver(new URL("http://192.168.29.251:4444/grid/register"),
				getBrowserCapabilities(browser));
	}

	private static DesiredCapabilities getBrowserCapabilities(String browserType) {
		DesiredCapabilities cap;
		String workingDirectory = System.getProperty("user.dir");
		String filePath = workingDirectory + File.separator + File.separator
				+ "\\src\\main\\resources\\chromedriver.exe";

		System.out.println("Final file path : " + filePath);

		switch (browserType) {
		case "firefox":
			System.out.println("Opening firefox driver");
			cap = new DesiredCapabilities();
			FirefoxOptions options1 = new FirefoxOptions();
			options1.setAcceptInsecureCerts(true);
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			cap.merge(options1);	
			return cap;

		case "chrome":

			System.setProperty("webdriver.chrome.driver", filePath);
			System.out.println("Opening chrome driver");
			cap = new DesiredCapabilities();
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			cap.merge(options);
			return cap;

		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			System.setProperty("webdriver.chrome.driver", filePath);
			System.out.println("Opening chrome driver");
			cap = new DesiredCapabilities();
			ChromeOptions options2 = new ChromeOptions();
			options2.setAcceptInsecureCerts(true);
			cap.merge(options2);
			return cap;
		}
	}
}