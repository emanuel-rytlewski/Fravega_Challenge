package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.RemoteWebDriver;


public class WebDriverManager {
	protected WebDriver driver;
	public String URL, Node;
	protected ThreadLocal<RemoteWebDriver> threadDriver = null;

	 LocalDate time = LocalDate.now(); 
	 long startTime;
	 long delta;
	 public void deltaTime(){
		 long currentTime = System.currentTimeMillis();
		 delta = currentTime - startTime;
	 }
	 
	 @Parameters({"Port"})
	 @BeforeMethod
	 public void setUp() throws Exception {
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver/chromedriver.exe");
			driver = new ChromeDriver();
			//driver = new RemoteWebDriver(new URL("http:127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
			driver.manage().window().maximize();
	 		driver.navigate().to("https://www.fravega.com/");
	 		
	    }	

	 public void initiateDriver(String Port) throws MalformedURLException {
     
		 if(Port.equalsIgnoreCase("9001")){
			 driver = new RemoteWebDriver(new URL("http:127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
			 driver.manage().window().maximize();
		 }
		 else if(Port.equalsIgnoreCase("9002")){
			 driver = new RemoteWebDriver(new URL("http:127.0.0.1:4444/wd/hub"), DesiredCapabilities.firefox());
			 driver.manage().window().maximize();
		 }
	 }
	 
	 @AfterMethod
	 public void tearDown(ITestResult result) {
		 System.out.println("El test "+result.getMethod().getDescription()+"(1= Paso satisfactoriamente - 2= No paso satisfactoriamente) resultó: "+result.getStatus());
		 driver.close();
		 driver.quit();
	 }
}

