package listener;

import io.qameta.allure.restassured.AllureRestAssured;

public class CustomTpl {
    private static final AllureRestAssured FILTER = new AllureRestAssured();

    private CustomTpl() {
    }

    public static CustomTpl customLogFilter() {
        return InitLogFilter.logFilter;
    }

    public AllureRestAssured withCustomTemplate() {
        FILTER.setRequestTemplate("http-request.ftl");
        FILTER.setResponseTemplate("http-response.ftl");
        return FILTER;
    }

    private static class InitLogFilter {
        private static final CustomTpl logFilter = new CustomTpl();
    }
}
