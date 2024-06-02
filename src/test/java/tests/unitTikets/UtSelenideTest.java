package tests.unitTikets;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;

public class UtSelenideTest {
    @BeforeEach
    public void initSettings() {
    }

    @Test
    public void firstSelenideTest() {
        Selenide.open("https://uniticket.ru/");
        SelenideElement header = $x("//h1");
        header.should(Condition.text("Поиск дешевых авиабилетов"));
    }
}
