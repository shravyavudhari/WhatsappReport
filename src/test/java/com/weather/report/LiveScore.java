package com.weather.report;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveScore {
	
	WebDriver driver;
	@Test
	public void sendLiveScore() throws InterruptedException, AWTException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.espncricinfo.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//div[@class='match-info match-info-HSB card scorecard'][1]")).click();
		driver.findElement(By.xpath("//button[@value='en']")).click();
		String score = driver.findElement(By.xpath("//div[@class='match-header']")).getText();
		System.out.println(score);
//		String data = driver.findElement(By.xpath("//div[@class='featured-scoreboard slick-slider-container']//following::div[@class='slick-slide slick-active'][1]")).getText();
//		System.out.println(data);
		
		
		driver.get("https://api.whatsapp.com/send?phone=919542856170");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(20000);

		StringSelection selectMsg = new StringSelection(score);
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
