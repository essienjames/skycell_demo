package pages;

import com.microsoft.playwright.Page;

public class ShipmentsPage extends BasePage {

    public ShipmentsPage(Page page) {
        super(page);
    }

    public void openAndWait() {
        String pageUrl = "https://cloud.test.skymind.com/track-and-trace/shipments?view=table";
        navigateAndWait(pageUrl);
    }

    public void assertAllRowsAreContainers() {
        assertColumnValues("Status", "Closed");
    }
}
