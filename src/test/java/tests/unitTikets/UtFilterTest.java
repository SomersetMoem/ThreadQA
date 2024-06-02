package tests.unitTikets;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.unitTikets.utPage.UtMainPage;
import tests.unitTikets.utPage.UtSearchPage;
import tests.wildberries.BaseTest;

import java.util.List;

public class UtFilterTest extends BaseTest {
    @BeforeEach
    public void openSite() {
        getWebDriver().get("https://uniticket.ru/");
    }

    @Test
    public void test() {
        int expectedDayTo = 3;
        int expectedDayBack = 5;

        UtSearchPage utSearchPage = new UtMainPage(getWebDriver())
                .setCityFrom("Казань")
                .setCityTo("Дубай")
                .setDayTo(expectedDayTo)
                .setDayBack(expectedDayBack)
                .search();

        int actualDayTo = utSearchPage.getMainDayTo();
        int actualDayBack = utSearchPage.getMainDayBack();
        Assertions.assertEquals(expectedDayTo, actualDayTo);
        Assertions.assertEquals(expectedDayBack, actualDayBack);

        List<Integer> daysTo = utSearchPage.getDaysTo();
        List<Integer> daysBack = utSearchPage.getDaysBack();

        boolean isAllDaysToOk = daysTo.stream().allMatch(a->a.equals(expectedDayBack));
        boolean isAllDaysToBack = daysTo.stream().allMatch(a->a.equals(expectedDayBack));
        Assertions.assertTrue(isAllDaysToOk);
        Assertions.assertTrue(isAllDaysToBack);
    }

}
