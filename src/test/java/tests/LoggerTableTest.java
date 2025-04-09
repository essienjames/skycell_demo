package tests;

import base.PlaywrightSetup;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import pages.LoggersPage;

public class LoggerTableTest extends PlaywrightSetup {

    @Test
    public void testFilterByNotPaired() {
        LoggersPage loggersPage = new LoggersPage(page);

        login();
        loggersPage.openAndWait();

        // Apply filter
        page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions()
                .setHasText("Not Paired"))
                .getByRole(AriaRole.CHECKBOX)
                .check();

        // Assert column header is visible
        page.getByRole(AriaRole.COLUMNHEADER)
                .filter(new Locator.FilterOptions()
                .setHasText("Pairing Status"))
                .isVisible();

        page.waitForLoadState();

        // Perform assertions of text in table rows (no need to work with pagination)
        /* todo In the case of pagination rather than check all pages of the UI (slow)
            * it would be better to assert against backend data via API or database query (recommended),
            * alternatively checking the filter sent a correct query to the backend can give some confidence (network-based validation)
        */
        loggersPage.assertAllRowsAreNotPaired();
    }
}
