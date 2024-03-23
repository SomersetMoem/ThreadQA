package tests;

import calc.CalcSteps;
import listener.RetryListenerTestNG;
import model.People;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Listeners(RetryListenerTestNG.class)
public class NgTests {
    @BeforeSuite
    public void setRetryAnalyzer(ITestContext context) {
        for (ITestNGMethod testNGMethod : context.getAllTestMethods()) {
            testNGMethod.setRetryAnalyzer(new RetryListenerTestNG());
        }
    }

    @AfterSuite
    public void saveFailed() {
        RetryListenerTestNG.saveFailedTests();
    }

    @Test
    public void sumTestNGTest1() {
        CalcSteps calcSteps = new CalcSteps();
        Assert.assertTrue(calcSteps.isPositive(-10));
    }

    @Test
    public void sumTestNGTest2() {
        CalcSteps calcSteps = new CalcSteps();
        Assert.assertTrue(calcSteps.isPositive(-20));
    }

    @DataProvider(name = "testUsers")
    public Object[] dataWithUsers() {
        People stas = new People("stas", 25, "male");
        People katy = new People("katy", 30, "female");
        People kosty = new People("kosty", 25, "male");
        return new Object[]{stas, katy, kosty};
    }
    @Test(dataProvider = "testUsers")
    public void testUsersWithRile(People people) {
        System.out.println(people.getName());
        Assert.assertTrue(people.getAge() > 18);
        //magic
        Assert.assertTrue(people.getName().contains("a"));
    }

    @DataProvider(name = "ips")
    public Object[][] testIpAddress() {
        List<String> ips = new ArrayList<>();
        ips.add("122.3.2.1");
        ips.add("123.3.2.1");
        ips.add("124.3.2.1");
        ips.add("125.3.2.1");
        ips.add("localHost");

        return ips.stream().map(a->new Object[]{a})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "ips")
    public void ipsTest(String ip) {
        System.out.println(ip);
        Assert.assertTrue(ip.matches("^([0-9]+(\\.|$)){4}"));
    }

    @Test(dataProviderClass = DataTestArguments.class, dataProvider = "diffArgs")
    public void calcTest(int a, String b) {
        Assert.assertEquals(convert(a), b);
    }

    private String convert(int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 5:
                return "five";
            default:
                return null;
        }
    }
}
