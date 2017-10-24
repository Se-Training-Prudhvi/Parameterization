package com.vd.testng;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by z013th3 on 11/17/16.
 */
public class ExcelExample {
    public static void runTest(String strSearchString, String strPageTitle) throws InterruptedException {

        // Start a browser driver and navigate to Google
		System.setProperty("webdriver.gecko.driver", "/Users/z013th3/Downloads/geckodriver");
		System.out.println("Opening firefox driver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");

        // Enter the search string and send it
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(strSearchString);
        element.submit();
        Thread.sleep(5000);

        // Check the title of the page
        if (driver.getTitle().equals(strPageTitle)) {
            System.out.println("Page title is " + strPageTitle + ", as expected");
        } else {
            System.out.println("Expected page title was " + strPageTitle + ", but was " + driver.getTitle() + " instead");
        }

        //Close the browser
        driver.quit();
    }

    public static void main (String args[]) throws InterruptedException {

        try {
            // Open the Excel file
            FileInputStream fis = new FileInputStream("/Users/z013th3/Documents/vDig/testdata.xlsx");
            // Access the required test data sheet

            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Sheet1");
            // Loop through all rows in the sheet
            // Start at row 1 as row 0 is our header row
            for(int count = 1;count<=sheet.getLastRowNum();count++){
                XSSFRow row = sheet.getRow(count);
                System.out.println("Running test case " + row.getCell(0).toString());
                // Run the test for the current test data row
                runTest(row.getCell(1).toString(),row.getCell(2).toString());
            }
            fis.close();
        } catch (IOException e) {
            System.out.println("Test data file not found");
        }
    }
}

