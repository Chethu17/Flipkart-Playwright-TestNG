package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.flipkartPage;

public class flipkartTest extends BaseTest {

	private flipkartPage flipkartPage;
    @BeforeClass
    public void createPageObject() {
        flipkartPage = new flipkartPage(page, context);
    }
	
	@Test
    public void verifySearchProductTest() {
        flipkartPage.searchProduct("books");
        page.waitForTimeout(2000); // just because i want to see
        flipkartPage.goBack();
    }
	
	@Test(dependsOnMethods = "verifySearchProductTest")
    public void verifySearchWithSUggetsion() {
        flipkartPage.searchAndSelectSuggestion("Shirt", "Shirt for women");
    }
	
	@Test(dependsOnMethods = "verifySearchWithSUggetsion")
    public void verifyFilterOptions() {
        flipkartPage.selectFilterOptions("fabric", "Cotton Blend");
    }
	
	@Test(dependsOnMethods = "verifyFilterOptions")
    public void verifyItemsLoadedAndList() {
        flipkartPage.listOfitemsFound();
    }
	
	@Test(dependsOnMethods = "verifyItemsLoadedAndList")
    public void verifyOpen2ndProduct() {
        flipkartPage.open2ndProduct();
    }
	
	@Test(dependsOnMethods = "verifyOpen2ndProduct")
    public void verifySelectSize() {
        flipkartPage.selectSize("XL");
    }
	
	@Test(dependsOnMethods = "verifySelectSize")
    public void verifyHover() {
        flipkartPage.hoverloginandFetch();
    }
}
