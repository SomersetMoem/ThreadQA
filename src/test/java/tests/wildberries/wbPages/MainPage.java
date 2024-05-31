package tests.wildberries.wbPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import tests.wildberries.BasePage;

public class MainPage extends BasePage {
    private final By searchField = By.id("searchInput");
    private final By basketButton = By.xpath("//a[@data-wba-header-name='Cart']");
    private final By loginButton = By.xpath("//a[@data-wba-header-name='Login']");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
        waitPageLoadsWb();
    }

    public SearchResultPage searchItem(String item) {
        webDriver.findElement(searchField).click();
        webDriver.findElement(searchField).sendKeys(item);
        webDriver.findElement(searchField).sendKeys(Keys.ENTER);
        return new SearchResultPage(webDriver);
    }
}
