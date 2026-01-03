package seleniumtests.browserfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserFactory {

    private static WebDriver driver;

    private static void createDriver() {

        // Reads browser type from JVM system properties.
        // Can be overridden from command line when running Maven tests.
        // Example: mvn test -Dbrowser=firefox
        String browser = System.getProperty("browser", "chrome");

        switch (browser.toLowerCase()) {
            case "firefox":
                createFirefoxDriver();
                break;
            case "chrome":
            default:
                createChromeDriver();
                break;
        }
        applyBaseSettings();
    }

    private static void createChromeDriver() {

        // Downloads the browser driver on the first run.
        // On subsequent runs, check the local cache and driver version.
        // Download a new version only if an update is required.
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        /* Enables headless mode only when the JVM system property "headless" is set to true.
         By default, headless is false (configured in pom.xml), so the browser UI is visible
         when running tests from IDE or via `mvn test`.
         Headless mode is enabled explicitly from command line, for example:
         example: mvn test -Dheadless=true
         The "--headless=new" flag is used for modern Chrome versions (109+),
         as the legacy "--headless" mode is deprecated.
         */
        if (isHeadless()) {
            options.addArguments("--headless=new");
        }

        driver = new ChromeDriver(options);
    }

    private static void createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        // Firefox uses the standard headless flag without the "new" option.
        //  mvn test -Dheadless=true
        if (isHeadless()) {
            options.addArguments("-headless");
        }

        driver = new FirefoxDriver(options);
    }

    /*
     * The "headless" property is declared in pom.xml with a default value (false)
     * as a configuration declaration and documentation of intended behavior.
     * This code does not define the value itself.
     * It reads the effective JVM system property, which can be overridden
     * from the command line (for example: mvn test -Dheadless=true).
     * In short:
     * pom.xml  — declares the default: <headless>false</headless>
     * CLI/JVM  — overrides the value
     * code     — applies the behavior (local runs / CI)
     */
    private static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    private static void applyBaseSettings() {
        if (!isHeadless()) {
            driver.manage().window().maximize();
        }

        // Implicit wait sets a global timeout for element lookup.
        // WebDriver waits only for the element to be present in the DOM,
        // not for it to be visible or interactable.
        // Waiting for visibility or other conditions requires explicit waits.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));


        /* Base URL of the test environment.
         Can be overridden at runtime, for example:
         mvn test -DbaseUrl=https://staging.example.com
        */
        driver.navigate().to(
                System.getProperty("baseUrl", "https://the-internet.herokuapp.com")
        );
    }

    /*
     * Simplified Singleton pattern.
     * Ensures that only one WebDriver instance is created
     * and shared across all tests during the test run.
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
