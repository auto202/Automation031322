package commonLibs.implimentations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commonLibs.contacts.IDriver;

public abstract class CommonDrivers implements IDriver {
	
	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
	
	private String currentWorkingDirectory;
	
	public CommonDrivers(String browserType)throws Exception {
		pageLoadTimeout = 60;
		elementDetectionTimeout= 10;
		
		currentWorkingDirectory = System.getProperty("user.dir"); // C:\AutomationRepo\AutomationTestProject

		if(browserType.equalsIgnoreCase("chrome")){

			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory+ "drivers/chromedriver.exe");

		} else if (browserType.equalsIgnoreCase("firefox")){

			System.setProperty("webdriver.gecko.driver", currentWorkingDirectory+ "drivers/geckodiver.exe");

		} else if (browserType.equalsIgnoreCase("IE")){

			System.setProperty("webdriver.IEDriverServer.driver", currentWorkingDirectory+ "drivers/IEDriverServer.exe");
		}
		else {
			throw new Exception ("Invalid Browser Type");
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public void navigationToFirstUrl(String url) throws Exception {
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
	}
	
	public String getTitle() throws Exception {
		String title = driver.getTitle();
		return title;
		
	}
	
	
	public String getCurrentUrl() throws Exception {
			
			return driver.getCurrentUrl();
	}	
	
	public String  getPageSource() throws Exception {
		return driver.getPageSource();
	}
	

	public void navigateBackword() throws Exception {
		
		driver.navigate().back();
	}
	
	public void navigateForword() throws Exception {
		
		driver.navigate().forward();
	}
	public void navigateToUrl(String url) throws Exception {
		driver.navigate().to(url);
	}
	public void refresh() throws Exception {
	
		driver.navigate().refresh();
	}
	public void closeAllBrowser() throws Exception {
		if(driver!= null) {
		
		driver.quit();
		}
	}
	
	public void closeBrowser() throws Exception {
		if(driver!= null) {
		driver.close();
		}
	}
}