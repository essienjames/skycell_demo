package pages;

import com.microsoft.playwright.Page;

public class AssetsPage extends BasePage {

    public AssetsPage(Page page) {
        super(page);
    }

    public void openAndWait() {
        String pageUrl = "https://cloud.test.skymind.com/asset-management/assets?view=table";
        navigateAndWait(pageUrl);
    }

    public void assertAllRowsAreContainers() {
        assertColumnValues("Asset Type", "Container");
    }
}
