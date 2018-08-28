package testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest 
{
	WebDriver driver= null;
	@Test
	public void loginTest()
	{
		String browser="chrome";
		
		
		if(browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.get("http://in.rediff.com");
		
		driver.findElement(By.xpath("//*[@id='homewrapper']/div[5]/a[3]/div/u")).click();
		driver.findElement(By.xpath("//*[@id='signin_info']/a[1]")).click();
		driver.findElement(By.id("useremail")).sendKeys("smingle@gmail.com");
		driver.findElement(By.id("emailsubmit")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("userpass"))));
		
		driver.findElement(By.id("userpass")).sendKeys("Test1234");
		driver.findElement(By.id("loginsubmit")).click();
		
		try
		{
			driver.switchTo().alert().accept();
		}
		catch (Exception e)
		{
			System.out.println("unexpected alert not present");
		}
						
		boolean result= isElementPresent("//*[@id='username']/a");
		Assert.assertTrue(result, "Login Failed");
	}
	
	public boolean isElementPresent (String xpathExp)
	{
		int s=driver.findElements(By.xpath(xpathExp)).size();
		if (s == 0)
			return false;
		else 
			return true;
	}
	
}
