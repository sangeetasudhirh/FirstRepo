package MyFirstProject.MyFirstProject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginFeature 
{
	String browsername="firefox";
	String baseurl="http://localhost/login.do";
	WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		LaunchBrowser launchbrowser = new LaunchBrowser();
		driver=launchbrowser.selectBrowser(browsername);
		driver.get(baseurl);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	

	@Test(description="validating login with Valid Credentials")
	public void Test1()
	{
		LoginPage loginpage = new LoginPage(driver);
		String val= loginpage.validLogin("admin", "manager");
		Assert.assertTrue(val.contains("Enter"),"fail to login");
	}
	
	@Test(description="validating login with In-Valid Credentials")
	public void Test2()
	{
		LoginPage loginpage = new LoginPage(driver);
		String val= loginpage.inValidLogin("admin", "manager1");
		Assert.assertTrue(val.contains("Please try again."),"may be login");
	}
	
	
	@AfterMethod
	public void teardown()
	{
		driver.manage().deleteAllCookies();
		driver.quit();	
	}
}
