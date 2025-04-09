package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.Assertions;

public class LoggersPage extends BasePage {

    public LoggersPage(Page page) {
        super(page);
    }

    public void openAndWait() {
        String pageUrl = "https://cloud.test.skymind.com/asset-management/loggers?view=table";
        navigateAndWait(pageUrl, "[data-testid='Pairing Status']");
    }

    public void assertAllRowsAreNotPaired() {
        // Dynamically find the 'Pairing Status' column index by scanning the table header row,
        // then use that index to locate the matching <td> elements in the body rows.
        Locator headers = page.locator("thead tr th .MuiBox-root.css-knflgd");
        int columnCount = headers.count();
        int pairingStatusIndex = -1;

        for (int i = 0; i < columnCount; i++) {
            String headerText = headers.nth(i).innerText().trim();
            if (headerText.equalsIgnoreCase("Pairing Status")) {
                pairingStatusIndex = i + 1; // nth-child is 1-based
                break;
            }
        }

        // Assert each cell in the found column says "Not Paired"
        Locator pairingStatusCells = page.locator("table tbody tr td:nth-child(" + pairingStatusIndex + ")"");
        int rowCount = pairingStatusCells.count();

        for (int i = 0; i < rowCount; i++) {
            String cellText = pairingStatusCells.nth(i).innerText().trim();
            Assertions.assertEquals("Not Paired", cellText, "Unexpected value in Pairing Status column");
        }
    }
}
