package com.vd.testng;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class Test2 {
	WebDriver driver;
	public static String appURL = "http://automationpractice.com/index.php";


	@AfterTest
	public void aftmeth() {

		driver.quit();

	}

	@Test
	@Parameters({ "email", "password", "browser" })
	public void listtheprice(String email, String password, String browser) throws InterruptedException {
		System.out.println("email passed as :- " + email);
		System.out.println("password passed as :- " + password);

		switch (browser) {
		case "firefox":
			System.out.println("browser passed in case firefox :- " + browser);
			System.setProperty("webdriver.gecko.driver", "/Users/z013th3/Downloads/geckodriver");
			System.out.println("Opening firefox driver");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.out.println("browser passed in case chrome :- " + browser);
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
		driver.findElement(By.id("email")).sendKeys("prudhvi.kothapalli@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("Selenium");
		driver.findElement(By.id("SubmitLogin")).click();
		WebElement ExWait = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.id("search_query_top")));
		ExWait.sendKeys("T-Shirts");
		driver.findElement(By.name("submit_search")).submit();
		WebElement ExWait1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(
				By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li/div/div[1]/div/a[1]/img")));
		ExWait1.click();
		String amount = driver.findElement(By.id("our_price_display")).getText();
		System.out.println(amount);
	}

	@Test(priority=1)
	public void addtocart() {
		String amount = driver.findElement(By.id("our_price_display")).getText();
		System.out.println(amount);
		driver.findElement(By.id("add_to_cart")).click();
	}

}
