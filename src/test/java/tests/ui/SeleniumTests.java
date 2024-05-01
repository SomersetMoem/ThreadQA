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

    @AfterEach
    public void close() {
        webDriver.close();
    }

    @Test
    public void simpleTest() {
        String expectedTitle = "Олег Пендрак - Инженер по автоматизации тестирования QA Automation";

        webDriver.get("https://threadqa.ru/");
        String actualTitle = webDriver.getTitle();

        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void simpleFormTest() {
        webDriver.get("http://85.192.34.140:8081/");
        WebElement elementCard = webDriver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
        elementCard.click();

        WebElement elementTextBox = webDriver.findElement(By.xpath("//span[text()='Text Box']"));
        elementTextBox.click();

        WebElement fullName = webDriver.findElement(By.id("userName"));
        WebElement email = webDriver.findElement(By.id("userEmail"));
        WebElement currentAddress = webDriver.findElement(By.id("currentAddress"));
        WebElement permanentAddress = webDriver.findElement(By.id("permanentAddress"));
        WebElement submit = webDriver.findElement(By.id("submit"));

        String expectedName = "Tomas Anders";
        String expectedEmail = "tomas@asd.ru";
        String expectedCurrentAddress = "USA Los Angeles";
        String expectedPermanentAddress = "USA Miami";

        fullName.sendKeys(expectedName);
        email.sendKeys(expectedEmail);
        currentAddress.sendKeys(expectedCurrentAddress);
        permanentAddress.sendKeys(expectedPermanentAddress);
        submit.click();

        WebElement nameNew = webDriver.findElement(By.id("name"));
        WebElement emailNew = webDriver.findElement(By.id("email"));
        WebElement currentAddressNew = webDriver.findElement(By.xpath("//div[@id='output']//p[@id='currentAddress']"));
        WebElement permanentAddressNew = webDriver.findElement(By.xpath("//div[@id='output']//p[@id='permanentAddress']"));

        String actualName = nameNew.getText();
        String actualEmail = emailNew.getText();
        String actualCurrentAddress = currentAddressNew.getText();
        String actualPermanentAddress = permanentAddressNew.getText();

        Assertions.assertTrue(actualName.contains(expectedName));
        Assertions.assertTrue(actualEmail.contains(expectedEmail));
        Assertions.assertTrue(actualCurrentAddress.contains(expectedCurrentAddress));
        Assertions.assertTrue(actualPermanentAddress.contains(expectedPermanentAddress));
    }

    @Test
    public void uploadTest() {
        webDriver.get("http://85.192.34.140:8081/");
        WebElement elementCard = webDriver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Elements']"));
        elementCard.click();

        WebElement elementUploadAndDownload = webDriver.findElement(By.xpath("//span[text()='Upload and Download']"));
        elementUploadAndDownload.click();

        WebElement uploadBtn = webDriver.findElement(By.id("uploadFile"));
        uploadBtn.sendKeys(System.getProperty("user.dir") + "/src/test/resources/threadqa.jpeg");

        WebElement uploadFakePath = webDriver.findElement(By.id("uploadedFilePath"));
        Assertions.assertTrue(uploadFakePath.getText().contains("threadqa.jpeg"));
    }

    @Test
    public void testDownload() {

    }
}
