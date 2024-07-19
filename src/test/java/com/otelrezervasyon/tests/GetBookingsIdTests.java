package com.otelrezervasyon.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingsIdTests extends BaseTest{

    @Test
    public void getBookingByıd() {

        Response response = given(spec)
                .when()
                .get("/booking/" + createBookingId());

        response
                .then()
                .statusCode(200);

        String firstname = response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("ali", firstname);
        String lastname = response.jsonPath().getJsonObject("lastname");
        Assertions.assertEquals("pamuk", lastname);
        int totalprice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals(300, totalprice);
        





    }
}
