import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class Generator {
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void makeRequest(Registration registration) {
        given()
                .spec(requestSpecification)
                .body(registration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
    public static Registration getValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return registration;
    }

    public static Registration getValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "blocked";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return registration;
    }

    public static Registration getUserWithIncorrectPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = "password";
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return new Registration(login, "incorrectpassword", status);
    }

    public static Registration getUserWithIncorrectLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = "vasya";
        String password = faker.internet().password();
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return new Registration("petya", password, status);
    }
//    public static Registration registrationOfActiveUser() {
//        Registration registrationOfActiveUser = new Registration();
//        Gson gsonBuilder = new GsonBuilder().create();
//        String jsonClientData = gsonBuilder.toJson(registrationOfActiveUser);
//        given()
//                .spec(requestSpecification)
//                .body(jsonClientData)
//                .when()
//                .post("/api/system/users")
//                .then()
//                .statusCode(200);
//        return registrationOfActiveUser;
//    }
//
//    public static Registration registrationOfBlockedUser() {
//        Registration registration = generateAutharizationForBlockedUser();
//        Gson gsonBuilder = new GsonBuilder().create();
//        String jsonClientData = gsonBuilder.toJson(registration);
//        given()
//                .spec(requestSpecification)
//                .body(jsonClientData)
//                .when()
//                .post("/api/system/users")
//                .then()
//                .statusCode(200);
//        return registration;
//    }
}
