package unittests;

import com.javacorepractice.DemoUnitTestService;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoUnitTestServiceTest {

    private final DemoUnitTestService service = new DemoUnitTestService();

    // Test 1: simple assertion (hard assert)
    @Test
    public void testSumWithHardAssert() {
        int result = service.sum(2, 3);
        Assert.assertEquals(result, 5, "Sum should be 5");
    }

    // Test 2: another hard assert
    @Test
    public void testSumWithNegativeNumbers() {
        int result = service.sum(-2, 3);
        Assert.assertEquals(result, 1, "Sum should be 1");
    }

    // Test 3: soft assertions
    @Test
    public void testSumWithSoftAssert() {
        SoftAssert softAssert = new SoftAssert();

        int result = service.sum(10, 5);

        softAssert.assertEquals(result, 15, "Sum check");
        /*
        SoftAssert allows all assertions to be executed even if some of them fail;
        test will fail only after assertAll(), showing all assertion errors at once
        */
        softAssert.assertTrue(result > 100, "Result should be positive");
        softAssert.assertNotEquals(result, 15, "Result should not be zero");

        softAssert.assertAll(); // mandatory for soft asserts
    }
}
