package tests.unitTikets.utPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class UtMainSelenidePage {
    private SelenideElement cityFromField = $x("//input[@placeholder='Откуда']");
    private ElementsCollection listOfCityFrom = $$x("//div[@class='origin field active']//div[@class='city']");
    private SelenideElement cityToField = $x("//input[@placeholder='Куда']");
    private ElementsCollection listOfCityTo = $$x("//div[@class='destination field active']//div[@class='city']");
    private SelenideElement dateTo = $x("//input[@placeholder='Туда']");
    private SelenideElement dateBack = $x("//input[@placeholder='Обратно']");
    private String dayInCalendar = "//span[text()='%s']";
    private SelenideElement searchButton = $x("//div[@class='search_btn']");

    public UtMainSelenidePage setCityFrom(String cityFrom) {
        cityFromField.clear();
        cityFromField.sendKeys(cityFrom);
        cityFromField.click();
        listOfCityFrom.find(Condition.partialText(cityFrom)).click();
        return this;
    }

    public UtMainSelenidePage setCityTo(String cityTo) {
        cityToField.clear();
        cityToField.sendKeys(cityTo);
        cityToField.click();
        listOfCityTo.find(Condition.partialText(cityTo));
        return this;
    }

    public UtMainSelenidePage setDayTo(int day) {
        dateTo.click();
        getDay(day).click();
        $x("//h2").click();
        return this;
    }

    public UtMainSelenidePage setDayBack(int day) {
        dateBack.click();
        getDay(day).click();
        $x("//h2").click();
        return this;
    }

    private SelenideElement getDay(int day) {
        return $x(String.format(dayInCalendar, day));

    }

    public UtSearchSelenidePage search() {
        searchButton.click();
        return page(UtSearchSelenidePage.class);
    }
}
