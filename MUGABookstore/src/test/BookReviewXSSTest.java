package test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import helper.HelperLib;


@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:WebContent/WEB-INF/main-servlet.xml")
public class BookReviewXSSTest {
	
	public WebDriver wb;
	
	
	@Before
	public void initializeWebDriver() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		System.setProperty("webdriver.chrome.driver", "WebContent/resources/ChromeDriver/chromedriver.exe");
		wb = new ChromeDriver(options);
	}
	
	@Test
	public void insertXSSReview() {
		String rando = HelperLib.generateRandomString(20);
		
		wb.get("http://localhost:8080/MUGABookstore/bookinfo?bid=b0586");
		wb.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		
		WebElement we = wb.findElement(By.id("fourStar"));
		JavascriptExecutor executor = (JavascriptExecutor) wb;
		executor.executeScript("arguments[0].click();", we);
		

		wb.findElement(By.id("reviewInputContent")).sendKeys("<script>alert('Boom" + rando  +"')</script>");
		
		wb.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		wb.findElement(By.id("submitBid")).click();
		
		wb.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
		String inText = wb.findElement(By.xpath("//p[contains(text(),'" + rando + "')]")).getText();
		System.out.println(inText);
		assert(inText.contains("<script>") && inText.contains("</script>"));

	}

}
