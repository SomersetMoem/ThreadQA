package calc;

import io.qameta.allure.Step;

public class CalcSteps {
    @Step("Складываем числа {0} и {1}")
    public int sum(int a, int b) {
        return a+b;
    }

    @Step("Проверяем, что число {0} > 0")
    public boolean isPositive(int res) {
        return res > 0;
    }
}
