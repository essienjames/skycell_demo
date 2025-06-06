package tests;

import base.PlaywrightRunner;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import pages.LoggersPage;

public class LoggerTableTest extends PlaywrightRunner {

    @Test
    public void testFilterByNotPaired() {
        LoggersPage loggersPage = new LoggersPage(page);

        login();
        loggersPage.openAndWait();

        // Apply filter by 'Not Paired'
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

        // Perform assertions of text in table rows (no need to work with pagination)
        loggersPage.assertAllRowsAreNotPaired();
    }
}
