package com.vd.testng;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1

{
	WebDriver driver;
	public static String appURL = "http://automationpractice.com/index.php";

	@AfterTest
	public void aftmeth() {

		driver.quit();

	}

	@Test
	@Parameters({ "email", "password", "browser" })
	public void TestLogin(String email, String password, String browser) throws InterruptedException {
		System.out.println("email passed as :- " + email);
		System.out.println("password passed as :- " + password);
		System.out.println("browser passed as :- " + browser);

		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "/Users/z013th3/Downloads/geckodriver");
			System.out.println("Opening firefox driver");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "/Users/z013th3/Downloads/chromedriver");
			System.out.println("Opening chrome driver");
			driver = new ChromeDriver();
			break;
		default:
            System.out.println("browser : " + browser + " is invalid, Launching Firefox as browser of choice..");
            
		}
		
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();
	}

	@Test
	public void searchtshirt() {
		WebElement ExWait = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
		ExWait.sendKeys("T-Shirts");
		driver.findElement(By.name("submit_search")).submit();
	}
}