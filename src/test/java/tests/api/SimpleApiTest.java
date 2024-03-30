package tests.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class SimpleApiTest {

    /**
     * given() - начальная точка апи теста, которая позволяет описать предварительные настройки псевдо-естественным стилем.
     * <p>
     * contentType() - это заголовок который определяет в каком формате мы отправляем данные серверу
     * <p>
     * body() - это тело запроса
     * <p>
     * queryParam() - это параметр запроса после '?'
     * <p>
     * pathParam()
     * <p>
     * auth() - это выбор типа авторизации
     * <p>
     * cookie()
     * <p>
     * header()
     * <p>
     * multiPart() - загрузка изображений или файлов на сервер, где в
     * дополнение к самим файлам, вы также отправляете другие поля формы, как это делается в HTML формах при загрузке файлов.
     * <p>
     * then() - его роль описать ожидания по поводу того, как должен выглядеть ответ: какие заголовки он должен содержать,
     * какой должен быть его статус-код, какое тело должно быть и так далее.
     * <p>
     * jsonPath() - позволяет обратится к json ответу и вернуть требуемую структуру например в ответе есть большой json, а нам
     * нужен только опеределенный объект оттуда.
     * <p>
     * as() - позволяет преобразовать полученный json в модельный класс.
     * <p>
     * asPrettyString() - можете вызвать asPrettyString() после получения Response объекта для печати форматированного тела ответа.
     * Это особенно полезно когда вы работаете с большими или сложными JSON или XML, поскольку метод автоматически добавляет переводы строк
     * и отступы для улучшения читаемости.
     **/

    @Test
    public void getAllUsersTest() {
        given().get("https://fakestoreapi.com/users")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    public void getSingleUser() {
        int userId = 5;

        given().pathParam("userId", userId)
                .get("https://fakestoreapi.com/users/{userId}")
                .then().log().all()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}"));
    }
}



