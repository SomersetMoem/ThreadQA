package tests.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SeleniumTests {
    private WebDriver webDriver;

    @BeforeEach
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @Test
    public void simpleTest() {
        String expectedTitle = "Олег Пендрак - Инженер по автоматизации тестирования QA Automation";

        webDriver.get("https://threadqa.ru/");
        String actualTitle = webDriver.getTitle();

        Assertions.assertEquals(expectedTitle, actualTitle);
        webDriver.close();
    }
}
