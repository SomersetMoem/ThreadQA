package tests.wildberries.wbPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.wildberries.BasePage;

public class ItemPage extends BasePage {
    private final By nameItem = By.xpath("//div[@class='product-page__header']//h1");
    private final By priceItemText = By.xpath("//span[@class='price-block__wallet-price']");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public String getItemName() {
        return webDriver.findElement(nameItem).getText();
    }

    public Integer getItemPrice() {
        String priceText = getTextJs(priceItemText);
        priceText = priceText.replaceAll("[^0-9.]", "");
        return Integer.valueOf(priceText);
    }
}
