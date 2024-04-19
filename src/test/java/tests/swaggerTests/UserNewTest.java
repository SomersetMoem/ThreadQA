package tests.swaggerTests;

import assertions.AssertableResponse;
import assertions.Condition;
import assertions.Conditions;
import assertions.GenericAssertableResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import listener.CustomTpl;
import model.swagger.FullUser;
import model.swagger.Info;
import model.swagger.JwtAuthData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static assertions.Conditions.hasMessage;
import static assertions.Conditions.hasStatusCode;
import static io.restassured.RestAssured.given;

public class UserNewTest {

    private static Random random;
    private static UserService userService;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://85.192.34.140:8080/";
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter(),
                CustomTpl.customLogFilter().withCustomTemplate());
        random = new Random();
        userService = new UserService();
    }

    @Test
    public void positiveRegisterTest() {
        FullUser user = getRandomFullUser();

        userService.register(user)
                .should(hasStatusCode(201))
                .should(hasMessage("User created"));
    }

    @Test
    public void negativeRegisterLoginExistsTest() {
        FullUser user = getRandomFullUser();


        Info info = userService.getUserInfo().as(Info.class);

        Assertions.assertEquals("User created", info.getMessage());

        Info errorInfo = given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("Login already exist", errorInfo.getMessage());
        Assertions.assertEquals("fail", errorInfo.getStatus());
    }

    @Test
    public void negativeRegisterNoPasswordTest() {
        int randomNumber = Math.abs(random.nextInt());
        FullUser user = FullUser.builder()
                .login("threadQA" + randomNumber)
                .build();


        Info errorInfo = given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        new AssertableResponse(given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then())
                .should(hasMessage("Missing login or password"))
                .should(hasMessage("fail"))
                .should(hasStatusCode(400));

        new GenericAssertableResponse<Info>(given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then(), new TypeRef<Info>() {
        });

        Assertions.assertEquals("Missing login or password", errorInfo.getMessage());
        Assertions.assertEquals("fail", errorInfo.getStatus());
    }

    @Test
    public void positiveAdminAuthTest() {
        JwtAuthData jwtAuthData = new JwtAuthData("admin", "admin");

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);
    }

    @Test
    public void positiveNewUserAuthTest() {
        int randomNumber = Math.abs(random.nextInt());
        FullUser user = FullUser.builder()
                .login("threadQA" + randomNumber)
                .pass("asd11")
                .build();

        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("User created", info.getMessage());

        JwtAuthData jwtAuthData = new JwtAuthData(user.getLogin(), user.getPass());

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);
    }

    @Test
    public void negativeAuthTest() {
        JwtAuthData jwtAuthData = new JwtAuthData("asdasd11", "asda12123");

        given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(401);
    }

    @Test
    public void positiveGetUserInfoTest() {
        JwtAuthData jwtAuthData = new JwtAuthData("admin", "admin");

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        given().auth().oauth2(token).get("/api/user")
                .then()
                .statusCode(200);
    }

    @Test
    public void positiveGetUserInfoInvalidJwtTest() {
        given().auth().oauth2("some").get("/api/user")
                .then()
                .statusCode(200);
    }

    @Test
    public void negativeGetUserInfoWithoutJwtTest() {
        given().get("/api/user")
                .then()
                .statusCode(401);
    }

    @Test
    public void positiveChangeUserPassTest() {
        int randomNumber = Math.abs(random.nextInt());
        FullUser user = FullUser.builder()
                .login("threadQA" + randomNumber)
                .pass("asd11")
                .build();

        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("User created", info.getMessage());

        JwtAuthData jwtAuthData = new JwtAuthData(user.getLogin(), user.getPass());

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        Map<String, String> password = new HashMap<>();
        String updatePassword = "newPas";
        password.put("password", updatePassword);

        Info updatePass = given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(password)
                .put("/api/user")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("User password successfully changed", updatePass.getMessage());

        jwtAuthData.setPassword(updatePassword);

        token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        FullUser updatedUser = given().auth().oauth2(token).get("/api/user")
                .then()
                .statusCode(200)
                .extract().as(FullUser.class);

        Assertions.assertNotEquals(user.getPass(), updatedUser.getPass());
    }

    @Test
    public void positiveChangeAdminPassTest() {
        JwtAuthData jwtAuthData = new JwtAuthData("admin", "admin");

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        Map<String, String> password = new HashMap<>();
        String updatePassword = "newPas";
        password.put("password", updatePassword);

        Info updatePass = given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(password)
                .put("/api/user")
                .then()
                .statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("Cant update base users", updatePass.getMessage());
    }

    @Test
    public void negativeDeleteAdminTest() {
        JwtAuthData jwtAuthData = new JwtAuthData("admin", "admin");

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        Info info = given().auth().oauth2(token)
                .delete("/api/user")
                .then()
                .statusCode(400)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("Cant delete base users", info.getMessage());
    }

    @Test
    public void positiveDeleteNewUserTest() {
        int randomNumber = Math.abs(random.nextInt());
        FullUser user = FullUser.builder()
                .login("threadQA" + randomNumber)
                .pass("asd11")
                .build();

        Info info = given().contentType(ContentType.JSON)
                .body(user)
                .post("/api/signup")
                .then()
                .statusCode(201)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("User created", info.getMessage());

        JwtAuthData jwtAuthData = new JwtAuthData(user.getLogin(), user.getPass());

        String token = given().contentType(ContentType.JSON)
                .body(jwtAuthData)
                .post("/api/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);

        Info infoDelete = given().auth().oauth2(token)
                .delete("/api/user")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("info", Info.class);

        Assertions.assertEquals("User successfully deleted", infoDelete.getMessage());
    }

    @Test
    public void positiveGetAllUsersTest() {
        List<String> users = given().get("/api/users")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<String>>() {
                });

        Assertions.assertTrue(users.size() >= 3);
    }

    private FullUser getRandomFullUser() {
        int randomNumber = Math.abs(random.nextInt());
        return FullUser.builder()
                .login("threadQA" + randomNumber)
                .pass("asd11")
                .build();
    }
}
