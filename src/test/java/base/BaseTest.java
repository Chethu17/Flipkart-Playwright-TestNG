package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import utilities.configReader;

public class BaseTest {
	  	protected Playwright playwright;
	    protected Browser browser;
	    protected BrowserContext context;
	    protected Page page;

	    @BeforeClass
	    public void setUp() {
	        playwright = Playwright.create();
	        configReader.loadProperties();
	        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(configReader.getProperty("headless"))));
	        context = browser.newContext();
	        page = context.newPage();
	        page.navigate(configReader.getProperty("url"));
	        
	      //close pop up
	      	try {
	      		page.getByRole(AriaRole.BUTTON).filter(new Locator.FilterOptions().setHasText("✕")).click();	
	      		}
	      	catch (Exception e) {
	      		System.out.println("No login pop up");
	      	}
	    }

	    @AfterClass
	    public void tearDown() {
	        page.close();
	        context.close();
	        browser.close();
	        playwright.close();
	    }
}
