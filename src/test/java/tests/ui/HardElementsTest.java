package tests.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class HardElementsTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @AfterEach
    public void close() {
        webDriver.close();
    }

    @Test
    public void authTest() {
        webDriver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
        String h3 = webDriver.findElement(By.xpath("//h3")).getText();
        Assertions.assertEquals("Basic Auth", h3);
    }

    @Test
    public void alertOk() {
        String expectedText = "I am a JS Alert";
        String expectedResult = "You successfully clicked an alert";
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
        webDriver.findElement(By.cssSelector("[onclick='jsAlert()']")).click();
        String actualText = webDriver.switchTo().alert().getText();
        webDriver.switchTo().alert().accept();
        String result = webDriver.findElement(By.id("result")).getText();
        Assertions.assertEquals(expectedText, actualText);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void iframeTest() {
        webDriver.get("https://mail.ru/");
        webDriver.findElement(By.xpath("//div[@id='mailbox']//button[text()='Войти']")).click();

        WebElement iframe = webDriver.findElement(By.xpath("//iframe[@class='ag-popup__frame__layout__iframe']"));
        webDriver.switchTo().frame(iframe);

        webDriver.findElement(By.cssSelector("input[name='username']")).sendKeys("asd");
    }

    @Test
    public void sliderTest() {
        webDriver.get("http://85.192.34.140:8081/");
        WebElement elementCard = webDriver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Widgets']"));
        elementCard.click();

        WebElement elementTextBox = webDriver.findElement(By.xpath("//span[text()='Slider']"));
        elementTextBox.click();

        WebElement slider = webDriver.findElement(By.xpath("//input[@type='range']"));
        //1 Вариант перемещения слайдера
        Actions actions = new Actions(webDriver);
        actions.dragAndDropBy(slider, 10, 0).build().perform();

        //2 Вариант перемещения слайдера
        int expectedValue = 85;
        int currentValue = Integer.parseInt(slider.getAttribute("value"));
        int valueToMove = expectedValue - currentValue;
        for (int i = 0; i < valueToMove; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        WebElement sliderValue = webDriver.findElement(By.id("sliderValue"));
        int sliderValueInteger = Integer.parseInt(sliderValue.getAttribute("value"));
        Assertions.assertEquals(expectedValue, sliderValueInteger);
    }

    @Test
    public void hover() {
        webDriver.get("http://85.192.34.140:8081/");
        WebElement elementCard = webDriver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Widgets']"));
        elementCard.click();

        WebElement elementTextBox = webDriver.findElement(By.xpath("//span[text()='Menu']"));
        elementTextBox.click();

        WebElement menuItemMiddle = webDriver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(menuItemMiddle).build().perform();

        WebElement subList = webDriver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subList).build().perform();

        List<WebElement> lastElements = webDriver.findElements(By.xpath("//a[contains(text(), 'Sub Sub Item')]"));
        Assertions.assertEquals(2, lastElements.size());
    }
}
