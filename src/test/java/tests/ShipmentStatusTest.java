package tests;

import base.PlaywrightRunner;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import pages.ShipmentsPage;

import java.util.List;

public class ShipmentStatusTest extends PlaywrightRunner {

    @Test
    public void testFilterByContainer() {
        ShipmentsPage shipmentsPage = new ShipmentsPage(page);

        login();
        shipmentsPage.openAndWait();

        // Clear default filters
        List<String> filtersToDeselect = List.of("Not Started", "In Transit");
        shipmentsPage.clearFilters(filtersToDeselect);

        // Apply filter by 'Container'
        page.getByRole(AriaRole.LISTITEM)
                .filter(new Locator.FilterOptions()
                        .setHasText("Closed"))
                .getByRole(AriaRole.CHECKBOX)
                .check();

        // Wait for table to update
        page.locator("table tbody tr").first().waitFor();

        // Assert column header is visible
        page.getByRole(AriaRole.COLUMNHEADER)
                .filter(new Locator.FilterOptions()
                        .setHasText("Reference / PO Number"))
                .isVisible();

        page.waitForLoadState();

        // Perform assertions of text in table rows (no need to work with pagination)
        shipmentsPage.assertAllRowsAreContainers();
    }
}
