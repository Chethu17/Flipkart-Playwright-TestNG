package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class BasePage {

	protected Page page;
	 protected BrowserContext context;

	    public BasePage(Page page, BrowserContext context) {
	        this.page = page;
	        this.context = context;
	    }
}
