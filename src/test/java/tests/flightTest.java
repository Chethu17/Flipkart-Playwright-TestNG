package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.flightPage;

public class flightTest extends BaseTest {

	@Test
	public void flightSearchTest() {

	    flightPage flightPage = new flightPage(page, context);
	    
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

}
