package pages;

import com.microsoft.playwright.Page;

public class LoggersPage extends BasePage {

    public LoggersPage(Page page) {
        super(page);
    }

    public void openAndWait() {
        String pageUrl = "https://cloud.test.skymind.com/asset-management/loggers?view=table";
        navigateAndWait(pageUrl);
    }

    public void assertAllRowsAreNotPaired() {
        assertColumnValues("Pairing Status", "Not Paired");
    }
}
