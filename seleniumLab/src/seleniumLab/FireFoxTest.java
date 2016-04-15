package seleniumLab;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxTest {

	private WebDriver driver;
	private String baseUrl;
	
	@Before
	public void setUp() throws Exception {
		 driver = new FirefoxDriver();
		 baseUrl = "http://www.ncfxy.com/";
	}

	@Test
	public void test() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("D://info.csv"));
			String line = null;
			while((line = reader.readLine()) != null){
				String item[] = line.split(",");
				String id = item[0];
				String email = item[1];
				String pwd = id.substring(4);
				
				driver.get(baseUrl);
				
				WebElement nameEle = driver.findElement(By.id("name"));
				nameEle.sendKeys(id);
				
				WebElement pwdEle = driver.findElement(By.id("pwd"));
				pwdEle.sendKeys(pwd);
				
				WebElement submitBtn = driver.findElement(By.id("submit"));
				submitBtn.click();
				
				WebElement infoText = driver.findElement(By.xpath("//*[@id='table-main']/tr[1]/td[2]"));
				String info = infoText.getText();
				
				assertEquals(email, info);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 @After
	  public void tearDown() throws Exception {
		//driver.quit();
		 driver.close();
	  }
	  

}
