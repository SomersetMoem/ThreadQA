package tests.wildberries;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.wildberries.wbPages.ItemPage;
import tests.wildberries.wbPages.MainPage;

import java.util.Locale;

public class WbFilterTests extends BaseTest {
    @BeforeEach
    public void openSite() {
        getWebDriver().get("https://www.wildberries.ru/");
    }
    @Test
    public void searchResultTest() {
        String expectedItem = "iPhone";
        Integer expectedPriceMax = 60_000;
        Integer expectedPriceMin = 36_000;

        ItemPage itemPage = new MainPage(getWebDriver())
                .searchItem(expectedItem)
                .openFilters()
                .setMinPrice(expectedPriceMin)
                .setMaxPrice(expectedPriceMax)
                .applyFilter()
                .openItem();

        String actualName = itemPage.getItemName();
        Integer actualPrice = itemPage.getItemPrice();

        Assertions.assertTrue(actualName.toLowerCase(Locale.ROOT).contains(expectedItem.toLowerCase(Locale.ROOT)));
        Assertions.assertTrue(actualPrice >= expectedPriceMin && actualPrice <= expectedPriceMax);
    }
}
