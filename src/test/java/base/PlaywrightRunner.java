package base;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import pages.LoginPage;

import java.nio.file.Paths;

public class PlaywrightRunner {
    protected static Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected LoginPage loginPage;

    @BeforeAll
    static void globalSetup() {
        playwright = Playwright.create();
    }

    @BeforeEach
    void setup() {
        // todo set headless to true by default
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100)
        );
        context = browser.newContext();

        // Start tracing
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true)
        );

        page = context.newPage();
        loginPage = new LoginPage(page);
    }

    @AfterEach
    void teardown() {
        // Stop Tracing
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("traces/trace.zip"))
        );

        context.close();
        browser.close();
    }

    @AfterAll
    static void globalTeardown() {
        playwright.close();
    }

    protected void login() {
        loginPage.login();
    }
}

