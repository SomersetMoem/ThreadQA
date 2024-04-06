package tests.api;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import model.fakeApiUser.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SimpleRefactoring {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());
    }

    @Test
    public void getAllUsersTest() {
        given().get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void getSingleUser() {
        int userId = 5;

        UserRoot response = given().pathParam("userId", userId)
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .extract().as(UserRoot.class);

        Assertions.assertEquals(response.getId(), userId);
        Assertions.assertTrue(response.getAddress().getZipcode().matches("\\d{5}-\\d{4}"));
    }

    @Test
    public void getAllUsersWithLimit() {
        int limitSize = 5;

        List<UserRoot> users = given().queryParam("limit", limitSize)
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<UserRoot>>() {
                });

        Assertions.assertEquals(limitSize, users.size());
    }

    @Test
    public void getAllUsersSortByDesc() {
        String sortedType = "desc";

        List<UserRoot> sortedResponse = given().queryParam("sort", sortedType)
                .get("/users")
                .then()
                .extract().as(new TypeRef<List<UserRoot>>() {
                });

        List<UserRoot> notSortedResponse = given().get("/users")
                .then()
                .extract().as(new TypeRef<List<UserRoot>>() {
                });

        List<Integer> sortedResponseIds = sortedResponse.stream().map(UserRoot::getId).collect(Collectors.toList());
        List<Integer> sortedByCode = notSortedResponse.stream().map(UserRoot::getId).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        Assertions.assertNotEquals(sortedResponse, notSortedResponse);
        Assertions.assertEquals(sortedResponseIds, sortedByCode);
    }

    @Test
    public void addUserTest() {
        UserRoot user = getTestUser();

        Integer ids = given().body(user)
                .post("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("id");

        Assertions.assertNotNull(ids);
    }

    @Test
    public void updateUserTest() {
        UserRoot user = getTestUser();
        String oldPassword = user.getPassword();
        user.setPassword("newpass11");

        UserRoot updateUser = given().body(user)
                .pathParam("userId", user.getId())
                .put("/users{userId}")
                .then()
                .extract().as(UserRoot.class);

        Assertions.assertNotEquals(updateUser.getPassword(), oldPassword);
    }

    private UserRoot getTestUser() {
        Name name = new Name("Thomas", "Anderson");
        Geolocation geolocation = new Geolocation("-37.3159", "81.1496");

        Address address = Address.builder()
                .city("Москва")
                .number(100)
                .zipcode("1245-1234")
                .street("Noviy arbat 12")
                .geolocation(geolocation)
                .build();

        return UserRoot.builder()
                .name(name)
                .phone("79000000000")
                .email("fake@mail.com")
                .username("thomasAdmin")
                .password("1q2w3e4r5")
                .address(address)
                .build();
    }

    @Test
    public void authUserTest() {
        AuthData authData = new AuthData("jimie_k", "klein*#%*");

        String token = given().contentType(ContentType.JSON)
                .body(authData)
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");

        Assertions.assertNotNull(token);
    }
}
