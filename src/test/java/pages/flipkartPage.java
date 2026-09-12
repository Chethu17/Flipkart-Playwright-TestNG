package pages;

import java.util.List;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import base.BasePage;

public class flipkartPage extends BasePage {
	
	private Page newpage;
	
	public flipkartPage(Page page, BrowserContext context) {
        super(page, context);
    }

    public void searchProduct(String product) {
        page.getByPlaceholder("Search for Products, Brands and More").first().fill(product);
        page.getByPlaceholder("Search for Products, Brands and More").first().press("Enter");
    }
    
    public void searchAndSelectSuggestion(String product, String suggestion) {
    	page.getByPlaceholder("Search for Products, Brands and More").first().fill(product);
    	page.locator(".Swx5kP").filter(new Locator.FilterOptions().setHasText(suggestion)).first().click();
    }
    
    
    public void selectFilterOptions(String filterElement, String filterOption) {
    	page.waitForTimeout(2000);
    	page.locator("//div[@class='_6odwB UHMz4K'][normalize-space()='"+filterElement+"']").click();
		Locator filterOptionCheckbox = page.locator("label").filter(new Locator.FilterOptions().setHasText(filterOption));
		filterOptionCheckbox.click();
		page.locator("//div[@class='_6odwB UHMz4K'][normalize-space()='"+filterElement+"']").click();
		//verify check box
		PlaywrightAssertions.assertThat(filterOptionCheckbox.locator("input[type='checkbox']")).isChecked();
    }
    
    public void listOfitemsFound() {
    	Locator products= page.locator("div[data-id]");
		System.out.println("total - "+products.count());
		System.out.println(products.nth(0).innerText()); //print 1st result
    }
    
    public void open2ndProduct() {
    	Locator products= page.locator("div[data-id]");
    	newpage = context.waitForPage(()-> products.nth(1).click()); //selecting 2nd result can be controlled if u wish
    }
    
    public void selectSize(String size) {
    	newpage.getByText(size, new Page.GetByTextOptions().setExact(true)).click();
    }
    
    //optional extra for learning
    public void hoverloginandFetch() {
    	page.bringToFront();
		page.getByText("Login").hover();
		
		Locator items= page.locator(".Li60rs");
		List<Locator> itemsList = items.all();
		for(Locator i : itemsList) {
			System.out.println(i.innerText());
		}
    }
    
    public void goBack() {
        page.goBack();
    }
    public void goForward() {
        page.goForward();
    }
}
