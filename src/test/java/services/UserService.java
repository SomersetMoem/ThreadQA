package services;

import assertions.AssertableResponse;
import io.restassured.http.ContentType;
import model.swagger.FullUser;
import model.swagger.JwtAuthData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserService {
    public AssertableResponse register(FullUser fullUser) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(fullUser)
                .post("/api/signup")
                .then());
    }

    public AssertableResponse getUserInfo(String jwt) {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .auth().oauth2(jwt)
                .get("/api/login")
                .then());
    }

    public AssertableResponse getUserInfo() {
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .get("/api/login")
                .then());
    }

    public AssertableResponse uploadPassword(String newPassword, String jwt) {
        Map<String, String> password = new HashMap<>();
        password.put("password", newPassword);

        return new AssertableResponse(given().contentType(ContentType.JSON)
                .auth().oauth2(jwt)
                .body(password)
                .put("/api/user")
                .then());
    }

    public AssertableResponse deleteUser(String jwt) {
        return new AssertableResponse(given().auth().oauth2(jwt)
                .delete("/api/user")
                .then());
    }

    public AssertableResponse auth(FullUser fullUser) {
        JwtAuthData data = new JwtAuthData(fullUser.getLogin(), fullUser.getPass());
        return new AssertableResponse(given().contentType(ContentType.JSON)
                .body(data)
                .post("/api/login")
                .then());
    }

    public AssertableResponse getAllUsers() {
      return new AssertableResponse(given().get("/api/users")
                .then());
    }
}
