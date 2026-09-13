package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import base.BaseTest;
import pages.flightPage;


public class flightTest extends BaseTest {
	
	private flightPage flightPage;
	
    @BeforeClass
    public void createPageObject() {
        flightPage = new flightPage(page, context);
    }
    
	@Test
	public void flightSearchTest() {
	    flightPage.selectOneway();
	    flightPage.selectRoundTrip();
	    flightPage.selectOrigin("Bengaluru");
	    flightPage.selectDestination("Mumbai");
	    flightPage.selectDepartureDate("September 2026","28");
	    page.waitForTimeout(2000);
	    flightPage.selectReturnDate("September 2026","30");
	    page.waitForTimeout(2000);
	    flightPage.searchFlights();	  
	}
	
	@Test
	public void popUpTest() {
		//alert and pop up for practice
		page.waitForTimeout(2000);
		page.navigate("https://rahulshettyacademy.com/AutomationPractice/");
		//page.onDialog(pop -> pop.accept());
		page.onceDialog(pop -> pop.accept());
		page.getByText("Alert", new Page.GetByTextOptions().setExact(true)).click();
		page.onceDialog(pop -> pop.dismiss());
		page.getByText("Confirm", new Page.GetByTextOptions().setExact(true)).click();
	}
	
	@Test
	public void framesTest() {
		//frames 
		page.navigate("https://demo.automationtesting.in/Frames.html");
		FrameLocator framepage= page.frameLocator("#singleframe");
		framepage.locator("input[type='text']").fill("I am Chethan M a software engineer in testing");
		page.waitForTimeout(2000);
	}
	

}
