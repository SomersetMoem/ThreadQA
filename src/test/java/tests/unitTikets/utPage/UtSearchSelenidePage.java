package tests.unitTikets.utPage;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class UtSearchSelenidePage {
    private SelenideElement title = $x("//div[@class='countdown-title']");
    private SelenideElement priceSelectedMain = $x("//li[@class='price--current']//span[@class='prices__price currency_font currency_font--rub']");
    private SelenideElement selectedDayTo = $x("//li[@class='price--current']/a//span[1]");
    private SelenideElement selectedDayBack = $x("//lri[@class='price--current']/a//span[3]");
    private ElementsCollection listOfToDays = $$x("//div[@class='ticket-action-airline-container']/following::span[@class='flight-brief-date__day'][1]");
    private ElementsCollection listOfBackDays = $$x("//div[@class='ticket-action-airline-container']/following::span[@class='flight-brief-date__day'][3]");

    public UtSearchSelenidePage assertAllDaysToShouldHaveDay(int expectedToDay) {
        String day = String.valueOf(expectedToDay);
        listOfToDays.should(CollectionCondition.containExactTextsCaseSensitive(day));
        return this;
    }

    public UtSearchSelenidePage assertAllDaysBackShouldHaveDay(int expectedBackDay) {
        String day = String.valueOf(expectedBackDay);
        listOfBackDays.should(CollectionCondition.containExactTextsCaseSensitive(day));
        return this;
    }

    public UtSearchSelenidePage waitForPage() {
        priceSelectedMain.should(Condition.matchText("\\d+"));
        return this;
    }

    public UtSearchSelenidePage waitForTitleDisappear() {
        title.should(Condition.disappear, Duration.ofSeconds(30));
        return this;
    }
}
