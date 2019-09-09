package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	/** Taking screenshots and store images in destination folder **/
	
	public static void takeSnapShot(WebDriver driver) throws IOException {
		String timeStamp;
		//String fileWithPath="C:\\Users\\rahul.sonawane\\Desktop\\AlphaSense_TestAutomtion\\automationpractice.com\\Screenshots\\screen.png";
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		//File DestFile = new File(fileWithPath);	
		try{
		// Copy file at destination
		FileUtils.copyFile(SrcFile,new File("C:\\Users\\rahul.sonawane\\Desktop\\AlphaSense_TestAutomtion\\automationpractice.com\\Screenshots\\screen.png"+timeStamp+".png"));
		System.out.println("the Screenshot is taken");
		}
		catch (IOException e) {             // TODO Auto-generated catch block             
		    e.printStackTrace();         
		  }  
	}

	/**
	 * 
	 * This function will check element presence
	 * 
	 * 
	 */
	public static boolean isPresent(WebDriver webdriver, By selector) {
		// try to find element by specified selector
		try {
			webdriver.findElement(selector);
		} catch (NoSuchElementException e) {
			// if element not exist return false
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * This function will wait unless element clickable
	 * 
	 * 
	 */
	
	public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.elementToBeClickable(selector));
		return element;
	}
	
	/**
	 * 
	 * This function will check element presence
	 * 
	 * 
	 */
	
	public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
		WebElement element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}
	
	/**
	 * 
	 * This function will wait for Title presence
	 * 
	 * 
	 */
	
	public static void waitForTitle(WebDriver driver, String title, int waitInterval){
		 (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.titleIs(title));
	}

}
