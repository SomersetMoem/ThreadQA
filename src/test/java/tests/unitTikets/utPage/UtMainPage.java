package tests.unitTikets.utPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.wildberries.BasePage;

public class UtMainPage extends BasePage {
    private By cityFromField = By.xpath("//input[@placeholder='Откуда']");
    private By listOfCityFrom = By.xpath("//div[@class='origin field active']//div[@class='city']");
    private By cityToField = By.xpath("//input[@placeholder='Куда']");
    private By listOfCityTo = By.xpath("//div[@class='destination field active']//div[@class='city']");
    private By dateTo = By.xpath("//input[@placeholder='Туда']");
    private By dateBack = By.xpath("//input[@placeholder='Обратно']");
    private String dayInCalendar = "//span[text()='%s']";
    private By searchButton = By.xpath("//div[@class='search_btn']");

    public UtMainPage(WebDriver driver) {
        super(driver);
    }

    public UtMainPage setCityFrom(String cityFrom) {
        webDriver.findElement(cityFromField).clear();
        webDriver.findElement(cityFromField).sendKeys(cityFrom);
        waitForTextPresentedInList(listOfCityFrom, cityFrom).click();
        return this;
    }

    public UtMainPage setCityTo(String cityFrom) {
        webDriver.findElement(cityToField).clear();
        webDriver.findElement(cityToField).sendKeys(cityFrom);
        waitForTextPresentedInList(listOfCityTo, cityFrom).click();
        return this;
    }

    public UtMainPage setDayTo(int day) {
        webDriver.findElement(dateTo).click();
        getDay(day).click();
        return this;
    }

    public UtMainPage setDayBack(int day) {
        webDriver.findElement(dateBack).click();
        getDay(day).click();
        return this;
    }

    private WebElement getDay(int day) {
        By dayBy = By.xpath(String.format(dayInCalendar, day));
        return webDriver.findElement(dayBy);
    }

    public void search() {
        webDriver.findElement(searchButton).click();
    }
}
