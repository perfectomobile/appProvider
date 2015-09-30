package test.java;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Reporter;




public class test1 extends basicTest{

	@Test (dataProvider="Devices") 
	public void f12(String DeviceId) {
		System.out.println("Test1 in Test"+DeviceId);
		RemoteWebDriver d = util.getRWD(DeviceId);
		try {
			this.execTest(d);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			this.endTest(d);

		}


	}

	@Override
	public void execTest(RemoteWebDriver driver) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" IN EXECUTE");
		Reporter.log(" TEST TO REPORTER");
		String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("***** Current dir:"+current);
        
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.get("http://www.awwwards.com");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);



		// 	WebDriverWait wait = new WebDriverWait(webdriver, 30);
		// 	wait.until(ExpectedConditions.elementToBeClickable()));
		driver.findElement(By.xpath("//*[@class='submit']"));


		// click the menu if mobile
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			driver.findElement(By.xpath("//*[@class='bt-menu']")).click();;

		} catch (Exception e) {
			// not a mobile - no menu 
		}
		driver.findElement(By.xpath("//*[@class='wrapper-nav']//child::*[text()='Nominees']")).click();;

		driver.findElement(By.xpath(".//*[@class='rollover site'][1]")).click();


	}

	@Override
	public void beforeTest() throws Exception {
		// TODO Auto-generated method stub

	}




	@Override
	public void endTest(RemoteWebDriver driver) {
		//close the test  
		try {
			String devId = (String) driver.getCapabilities().getCapability("deviceName");
			driver.close();				
			util.downloadReport(driver, "pdf", devId +"MS");	

			//htmlReporter.addLine(_RWD,"FIRST test", deviceID,util.getReprtName(deviceID, true), rc,"");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
		
			try{
				driver.quit();

			}catch(Exception e)
			{
				//  driver closed 
			}

		}

	}


}
