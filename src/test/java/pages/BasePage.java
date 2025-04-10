package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public abstract class BasePage {
    protected final Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigateAndWait(String url) {
        page.navigate(url);
        page.waitForLoadState();
    }

    public void clearFilters(List<String> filterConditions) {
        for (String condition : filterConditions) {
            page.getByRole(AriaRole.LISTITEM)
                    .filter(new Locator.FilterOptions()
                    .setHasText(condition))
                    .getByRole(AriaRole.CHECKBOX)
                    .uncheck();
        }
    }

    public void assertColumnValues(String columnHeaderText, String expectedValue) {
        page.waitForTimeout(2000);
        Locator tableHeaders = page.locator("thead tr th .MuiBox-root.css-knflgd");
        int columnCount = tableHeaders.count();
        int targetColumnIndex = -1;

        // Scan column headers to find the index of the target column
        for (int i = 0; i < columnCount; i++) {
            String headerText = tableHeaders.nth(i).innerText().trim();
            if (headerText.equalsIgnoreCase(columnHeaderText)) {
                targetColumnIndex = i + 1; // nth-child is 1-based
                break;
            }
        }

        // If target column is not found, throw an error
        if (targetColumnIndex == -1) {
            throw new AssertionError("Column with header '" + columnHeaderText + "' not found.");
        }

        // Perform final assertion on all rows
        Locator rows = page.locator("table tbody tr");
        int rowCount = rows.count();

        for (int i = 0; i < rowCount; i++) {
            String selector = "table tbody tr:nth-child(" + (i + 1) + ") td:nth-child(" + targetColumnIndex + ")";
            String cellText = page.locator(selector).innerText().trim();
            Assertions.assertEquals(expectedValue, cellText, "Unexpected value in '" + columnHeaderText + "' column at row " + (i + 1));
        }
    }
}
