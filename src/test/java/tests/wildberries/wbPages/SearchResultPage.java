package tests.wildberries.wbPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.wildberries.BasePage;

public class SearchResultPage extends BasePage {
    private final By allFiltersButton = By.xpath("//button[@class='dropdown-filter__btn dropdown-filter__btn--all']");
    private final By maxPriceField = By.xpath("//input[@name='endN']");
    private final By minPriceField = By.xpath("//input[@name='startN']");
    private final By applyFilterBtn = By.xpath("//button[@class='filters-desktop__btn-main btn-main']");
    private final By items = By.xpath("//div[@class='product-card-list']//article");

    public SearchResultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public SearchResultPage openFilters() {
        webDriver.findElement(allFiltersButton).click();
        return this;
    }

    public SearchResultPage applyFilter() {
        webDriver.findElement(applyFilterBtn).click();
        waitForElementUpdated(items);
        return this;
    }

    public ItemPage openItem() {
        webDriver.findElements(items).get(0).click();
        return new ItemPage(webDriver);
    }

    public SearchResultPage setMinPrice(Integer minPrice) {
        clearTextField(minPriceField);
        webDriver.findElement(minPriceField).sendKeys(String.valueOf(minPrice));
        return this;
    }

    public SearchResultPage setMaxPrice(Integer maxPrice) {
        clearTextField(maxPriceField);
        webDriver.findElement(maxPriceField).sendKeys(String.valueOf(maxPrice));
        return this;
    }
}
