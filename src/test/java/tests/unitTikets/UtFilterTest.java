package tests.unitTikets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.unitTikets.utPage.UtMainPage;
import tests.wildberries.BaseTest;

public class UtFilterTest extends BaseTest {
    @BeforeEach
    public void openSite() {
        getWebDriver().get("https://uniticket.ru/");
    }

    @Test
    public void test() {
        int expectedDayTo = 30;
        int expectedDayBack = 31;
        UtMainPage mainPage = new UtMainPage(getWebDriver());
        mainPage.setCityFrom("Казань")
                .setCityTo("Дубай")
                .setDayTo(expectedDayTo)
                .setDayBack(expectedDayBack)
                .search();
    }

}
