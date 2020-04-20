package test;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.containsString;



import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import bean.UserBean;
import dao.UserDAO;
import helper.HelperLib;
import model.UserModel;


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
		System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver.exe");
		wb = new ChromeDriver(options);
		wb.manage().window().maximize();
	}
	
	@Test
	public void insertXSSReview() {
		String rando = HelperLib.generateRandomString(20);
		
		wb.get("http://localhost:8080/MUGABookstore/bookinfo?bid=b0586");
		wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wb.findElement(By.id("fourStar")).click();

		wb.findElement(By.id("reviewInputContent")).sendKeys("<script>alert('Boom" + rando  +"')</script>");
		
		wb.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		wb.findElement(By.id("submitBid")).click();
		
		wb.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
		String inText = wb.findElement(By.xpath("//p[contains(text(),'" + rando + "')]")).getText();
		System.out.println(inText);
		assert(inText.contains("<script>") && inText.contains("</script>"));

	}

}
