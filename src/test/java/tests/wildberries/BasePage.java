package tests.wildberries;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BasePage {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.webDriver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
    }

    protected String getTextJs(By element) {
        return (String) js.executeScript("return arguments[0].textContent;", webDriver.findElement(element));
    }

    protected void clickElementJs(By element) {
        js.executeScript("arguments[0].click;", webDriver.findElement(element));
    }

    protected void waitPageLoadsWb() {
        By louder = By.xpath("//div[@class='general-preloader']");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(louder));
    }

    protected void waitForElementUpdated(By element) {
        wait.until(ExpectedConditions.stalenessOf(webDriver.findElement(element)));
    }

    protected void clearTextField(By element) {
        webDriver.findElement(element).clear();
        webDriver.findElement(element).sendKeys(Keys.LEFT_CONTROL + "A");
        webDriver.findElement(element).sendKeys(Keys.BACK_SPACE);
    }

    public WebElement waitForTextPresentedInList(By list, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(list));
        return webDriver.findElements(list).stream()
                .filter(x -> x.getText().contains(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Города нет в выпадающем списке: " + value));
    }

    public void waitForTextMatchesRegex(By locator, String regex) {
        Pattern pattern = Pattern.compile(regex);
        wait.until(ExpectedConditions.textMatches(locator, pattern));
    }

    public void waitForElementDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public Integer getDigitFromWebElement(WebElement element) {
        String text = element.getText().replaceAll("[^0-9.]", "");
        return Integer.parseInt(text);
    }

    public List<Integer> getDigitsFromList(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
       return webDriver.findElements(locator).stream()
                .filter(WebElement::isDisplayed)
                .map(this::getDigitFromWebElement)
                .collect(Collectors.toList());
    }
}
