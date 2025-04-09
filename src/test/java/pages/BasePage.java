package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateAndWait(String url, String waitForSelector) {
        page.navigate(url);
        Locator readyElement = page.locator(waitForSelector);
        readyElement.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }
}
