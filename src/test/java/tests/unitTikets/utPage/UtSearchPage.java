package tests.unitTikets.utPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.wildberries.BasePage;

import java.util.List;

public class UtSearchPage extends BasePage {
    private By title = By.cssSelector(".countdown-title");
    private By priceSelectedMain = By.xpath("//li[@class='price--current']//span[@class='prices__price currency_font currency_font--rub']");
    private By selectedDayTo = By.xpath("//li[@class='price--current']/a//span[1]");
    private By selectedDayBack = By.xpath("//li[@class='price--current']/a//span[3]");
    private By listOfToDays = By.xpath("//div[@class='ticket-action-airline-container']/following::span[@class='flight-brief-date__day'][1]");
    private By listOfBackDays = By.xpath("//div[@class='ticket-action-airline-container']/following::span[@class='flight-brief-date__day'][3]");

    public UtSearchPage(WebDriver driver) {
        super(driver);
    }

    public List<Integer> getDaysTo() {
        return getDigitsFromList(listOfToDays);
    }

    public List<Integer> getDaysBack() {
        return getDigitsFromList(listOfBackDays);
    }

    public Integer getMainDayTo() {
        return getDigitFromWebElement(webDriver.findElement(selectedDayTo));
    }

    public Integer getMainDayBack() {
        return getDigitFromWebElement(webDriver.findElement(selectedDayBack));
    }

    public void waitForPage() {
        waitForElementAppear(selectedDayTo);
        waitForTextMatchesRegex(priceSelectedMain, "\\d+");
    }

    public void waitForTitleDisappear() {
        waitForElementDisappear(title);
    }
}
