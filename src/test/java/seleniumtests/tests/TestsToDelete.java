package seleniumtests.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import seleniumtests.browserfactory.BrowserFactory;

/**
 * This class is created to test and validate settings.
 * In real-world projects, this kind of setup-validation code is usually not
 * committed to the repository and is used only locally or temporarily.
 * Since this is a training project, the class is intentionally kept in the codebase
 * to demonstrate all setup stages step by step.
 */


public class TestsToDelete {

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        BrowserFactory.quitDriver();
    }

    // This test verifies that the browser starts successfully
    @Test(groups = "smoke")
    public void test1() {
        BrowserFactory.getDriver();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
