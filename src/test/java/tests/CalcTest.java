package tests;

import calc.CalcSteps;
import io.qameta.allure.Allure;
import io.qameta.allure.Issue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class CalcTest {
    @Test
    public void test() {
        CalcSteps calcSteps = new CalcSteps();
        int res = calcSteps.sum(5, 1);
        boolean isOk = calcSteps.isPositive(res);
        Assertions.assertTrue(isOk);
    }

    @Test
    @Issue("GR-4")
    public void sumStepsTest() {
        int a = 5;
        int b = 6;
        AtomicInteger res = new AtomicInteger();
        Allure.step("Прибавляем "  + a + " к переменной " + b, step -> {
            res.set(a + b);
        });

        Allure.step("Проверяем, что результат " + res.get() + " больше нуля",step -> {
            Assertions.assertTrue(res.get() > 0);
        });
    }
}
