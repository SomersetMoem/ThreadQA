package tests.wildberries;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(list));
        return webDriver.findElements(list).stream()
                .filter(x -> x.getText().contains(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Города нет в выпадающем списке: " + value));
    }
}
