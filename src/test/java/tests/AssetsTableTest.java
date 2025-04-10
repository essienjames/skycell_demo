package tests;

import base.PlaywrightRunner;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import pages.AssetsPage;

public class AssetsTableTest extends PlaywrightRunner {

    @Test
    public void testFilterByContainer() {
        AssetsPage assetsPage = new AssetsPage(page);

        login();
        assetsPage.openAndWait();

        // Apply filter by 'Container'
        page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions()
                        .setHasText("Container"))
                .getByRole(AriaRole.CHECKBOX)
                .check();

        // Wait for table to update
        page.locator("table tbody tr").first().waitFor();

        // Assert column header is visible
        page.getByRole(AriaRole.COLUMNHEADER)
                .filter(new Locator.FilterOptions()
                        .setHasText("Asset Type"))
                .isVisible();

        page.waitForLoadState();

        // Perform assertions of text in table rows (no need to work with pagination)
        assetsPage.assertAllRowsAreContainers();
    }
}
