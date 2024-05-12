package tests.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

import java.time.Duration;

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

        WebElement slider = webDriver.findElement(By.cssSelector("[type='range']"));
        Action action = new
    }
}
