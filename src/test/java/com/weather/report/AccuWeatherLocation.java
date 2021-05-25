package com.weather.report;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccuWeatherLocation {

	WebDriver driver;

	@Test
	public void searchByLocation() throws AWTException, InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		driver = new ChromeDriver(options);

		driver.get("https://www.accuweather.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//input[@class='search-input']")).sendKeys("Hyderabad");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Enter the searchLocation :: ");
		String searchLocation = br.readLine();
		driver.findElement(By.xpath("//input[@class='search-input']")).sendKeys(searchLocation);

//		Actions action = new Actions(driver);
//		action.sendKeys(Keys.ESCAPE).build().perform();
		
		
		driver.findElement(By.xpath("//input[@class='search-input']")).click();
		driver.findElement(By.xpath("//div[@class='search-bar-result search-result'][1]")).click();

		String locationName = driver.findElement(By.xpath("//h1[@class='header-loc']")).getText();
//		System.out.println("Weather Report for "+location+" :");

		String curWeather = driver.findElement(By.xpath("//div[@class='cur-con-weather-card__panel'][1]")).getText();
//		System.out.println(curWeather);

		String todayWeather = driver.findElement(By.xpath("//div[@class='card weather-card content-module non-ad'][1]"))
				.getText();
//		System.out.println(todayWeather);

		String tonightWeather = driver
				.findElement(By.xpath("//div[@class='card weather-card content-module non-ad'][2]")).getText();
//		System.out.println(tonightWeather);

		String tomorrowWeather = driver
				.findElement(By.xpath("//div[@class='card weather-card content-module non-ad'][3]")).getText();
//		System.out.println(tomorrowWeather);

		StringBuilder newString = new StringBuilder("Weather Report for " + locationName + " :");
		newString.append("\n" + curWeather +"\n"+ todayWeather+"\n" + tonightWeather+"\n" + tomorrowWeather);
		String totalMessage = newString.toString();
		System.out.println(totalMessage);

		driver.get("https://api.whatsapp.com/send?phone=917794946568");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(20000);

		StringSelection selectMsg = new StringSelection(totalMessage);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectMsg, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);

	}

}
