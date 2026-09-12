package pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import base.BasePage;

public class flightPage extends BasePage {

	public flightPage(Page page, BrowserContext context) {
        super(page, context);
        //just navigate to main flight/travel section
        page.locator("a[href='/flights-travel-uhp-at-store?marketplace=FKT']").click();
    }
	
	public void selectRoundTrip() {
		 page.getByText("Round Trip",new Page.GetByTextOptions().setExact(true)).click();
	     //select one way and verify 
	     Locator addForDiscounts = page.getByText("Add for discounts",new Page.GetByTextOptions().setExact(true));
	     PlaywrightAssertions.assertThat(addForDiscounts).isHidden();
	}
	
	
	public void selectOneway() {
		 page.getByText("One Way",new Page.GetByTextOptions().setExact(true)).click();
	     //select one way and verify 
	     Locator addForDiscounts = page.getByText("Add for discounts",new Page.GetByTextOptions().setExact(true));
	     PlaywrightAssertions.assertThat(addForDiscounts).isVisible();
	}
	
    public void selectOrigin(String origin) {
    	page.locator(".css-g5y9jx.r-13awgt0.r-1kb76zh").click();      
        page.getByPlaceholder("Search origin city/airport").fill(origin);
        page.getByText(origin , new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectDestination(String destination) {
    	page.locator(".css-g5y9jx.r-13awgt0.r-1jkjb").first().click();
        page.getByPlaceholder("Search destination city/airport").fill(destination);
        page.getByText(destination , new Page.GetByTextOptions().setExact(true)).click();
    }

    public void selectDepartureDate(String monthYear, String day) {
        // date logic
    	Locator addForDiscounts = page.getByText("Add for discounts",new Page.GetByTextOptions().setExact(true));
    	boolean oneway = addForDiscounts.isVisible();
    	
    	page.getByText("Departure").click();
        
        boolean isInFirst = false;
        while (true) {
            Locator Month1 = page.locator(".css-g5y9jx.r-13awgt0.r-r2y082.r-1kb76zh");
            Locator Month2 = page.locator("div[class='css-g5y9jx r-13awgt0 r-1jkjb'] div[class='css-146c3p1']");
            if (Month1.innerText().contains(monthYear)) {
            	isInFirst = true;
                break;
            }
            else if(Month2.innerText().contains(monthYear)){
            	isInFirst = false;
            	break;
            }
            else {
                page.locator("svg[width='20']").click();
            }
        }
        
        if (isInFirst) {
        	page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[1]").click();
        	if(!oneway) {page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[1]").click();}
        	}
        else {
        	page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[2]").click();
        	if(!oneway) {page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[2]").click();}
        	}
    }

    public void selectReturnDate(String monthYear, String day) {
        // date logic
    	page.locator("//div[normalize-space()='Return']").click();
        
        boolean isInFirst = false;
        while (true) {
            Locator Month1 = page.locator(".css-g5y9jx.r-13awgt0.r-r2y082.r-1kb76zh");
            Locator Month2 = page.locator("div[class='css-g5y9jx r-13awgt0 r-1jkjb'] div[class='css-146c3p1']");
            if (Month1.innerText().contains(monthYear)) {
            	isInFirst = true;
                break;
            }
            else if(Month2.innerText().contains(monthYear)){
            	isInFirst = false;
            	break;
            }
            else {page.locator("svg[width='20']").click();}
        }
        
        if (isInFirst) {page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[1]").click();}
        else {page.locator("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[2]").click();}
    }
    
    public void searchFlights() {
        // search button
    	 page.getByText("Search flights").click();
         page.waitForTimeout(2000);
    }
}
