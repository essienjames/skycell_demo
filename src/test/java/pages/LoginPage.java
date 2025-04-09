package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.Assertions;

public class LoginPage extends BasePage {
    // TODO Load credentials from environment variables or secrets manager
    private final String email = "temp_user2@company2.com";
    private final String password = "09E8n^nR3nkT!UB0";

    // Locators
    private final Locator emailField;
    private final Locator passwordField;
    private final Locator loginButton;

    public LoginPage(Page page) {
        super(page);
        this.emailField = page.locator("input[name='username']");
        this.passwordField = page.locator("input[name='password']");
        this.loginButton = page.locator("button[type='submit']");
    }

    // Functions
    public void login() {
        String baseUrl = "https://cloud.test.skymind.com";
        page.navigate(baseUrl);

        // Fill in email and password fields
        emailField.fill(email);
        passwordField.fill(password);

        // Wait for the login button to be visible and enabled before interaction
        loginButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Assertions.assertTrue(loginButton.isEnabled(), "Login button should be enabled only after filling in credentials");

        // Click the login button
        loginButton.click();
    }
}
