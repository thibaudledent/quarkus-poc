package com.github.thibaudledent.quarkus.poc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/quizz")
          .then()
             .statusCode(200)
             .body(is("hello from quarkus-poc!"));
    }

}