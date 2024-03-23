package tests;

import org.testng.annotations.DataProvider;

public class DataTestArguments {
    @DataProvider(name = "argsForCalc")
    public Object[][] calcData() {
        return new Object[][]{{1,2,3}, {5,5,10}, {10,1,15}};
    }

    @DataProvider(name = "diffArgs")
    public Object[][] diffArgsObject() {
        return new Object[][] {{1, "one"}, {5, "five"}};
    }
}
