package tests.unitTikets;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import tests.unitTikets.utPage.UtMainSelenidePage;

public class UtSelenideTest {

    @Test
    public void firstSelenideTest() {
        int expectedDayTo = 3;
        int expectedDayBack = 5;
        Selenide.open("https://uniticket.ru/");

        UtMainSelenidePage mainPage = new UtMainSelenidePage();
        mainPage.setCityFrom("Казань")
                .setCityTo("Дубай")
                .setDayTo(expectedDayTo)
                .setDayBack(expectedDayBack)
                .search()
                .waitForPage()
                .waitForTitleDisappear()
                .assertAllDaysToShouldHaveDay(expectedDayTo)
                .assertAllDaysBackShouldHaveDay(expectedDayTo);
    }
}
