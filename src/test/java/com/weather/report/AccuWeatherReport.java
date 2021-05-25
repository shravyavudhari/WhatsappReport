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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.weather.excel.util.Xls_Reader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccuWeatherReport {

	WebDriver driver;
	Xls_Reader reader = new Xls_Reader("C:\\Users\\shravyav\\automation_WS\\WhatsappWeatherReport\\src\\main\\"
			+ "java\\com\\weather\\excel\\util\\WeatherReportExcel.xlsx");

	int rowNum = reader.getRowCount("Sheet1");
	

	@Test
	public void accuWeather() throws AWTException, InterruptedException {

		WebDriverManager.chromedriver().setup();

		// System.setProperty("webdriver.chrome.driver","C:\\Users\\shravyav\\Documents\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://www.accuweather.com/en/in/visakhapatnam/202192/weather-forecast/202192");
		driver.manage().window().maximize();
		Thread.sleep(1000);

		String curMsg = driver.findElement(By.xpath("//h2[@class='cur-con-weather-card__title']")).getText();
		String curTemp = driver.findElement(By.xpath(
						"//h2[@class='cur-con-weather-card__title']//following::div[@class='forecast-container'][1]"))
				.getText();
//		String CurWeather = curMsg + curTemp;

//		StringBuilder bldString = new StringBuilder(curMsg);
//        bldString.append(":").append(curTemp);
//        String singleString =  bldString.toString();
//		reader.removeColumn("Sheet1", 1);
//		reader.addColumn("Sheet1", "Message1");
//		reader.setCellData("Sheet1", "Message1", 2, singleString);
//		
		String tonightMsg = driver.findElement(By.xpath("//h2[contains(text(),'TONIGHT’S WEATHER FORECAST')]")).getText();
		String tonighTemp =  driver.findElement(By.xpath("//div[@class='card-content'][1]")).getText();
		
		StringBuilder bldString = new StringBuilder(curMsg);
      
        bldString.append(":"+" "+curTemp+"\n"+ tonightMsg+":"+" "+tonighTemp);	
//		bldString.append(":"+curTemp+"\n"+ tonightMsg).append("\n"+tonighTemp).append(System.getProperty("line.separator"));
        String singleString =  bldString.toString();
		reader.removeColumn("Sheet1", 1);
		reader.addColumn("Sheet1", "Message1");
		reader.setCellData("Sheet1", "Message1", 2, singleString);
		reader.setCellData("Sheet1", "Message1", 3, singleString);
		
		
		
//		StringBuilder bldString1 = new StringBuilder(tonightMsg);
//        bldString1.append(":").append(tonighTemp);
//        String singleString1 =  bldString1.toString();
//		
////		String tonightWeather = tonightMsg+tonighTemp;
//		reader.removeColumn("Sheet1", 2);
//		reader.addColumn("Sheet1", "Message2");
//		reader.setCellData("Sheet1", "Message2", 2, singleString1);
	}

	@Test
	public void sendWhatsappMessage() throws AWTException, InterruptedException {

		System.out.println(rowNum);

		for (int i = 2; i <= rowNum; i++) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(reader.getCellData("Sheet1", "UserName", i));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			System.out.println(driver.getCurrentUrl());

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(20000);
			
			String sendMessage = reader.getCellData("Sheet1", "Message1", 2);
			StringSelection selectMsg = new StringSelection(sendMessage);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectMsg, null);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);		
			
		}

//		List<WebElement> Elements  = driver.findElements(By.xpath("//div[@class='forecast-container']"));
//		for(WebElement element:Elements) {
//			System.out.println(element.getText());
//		}
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	}

}
