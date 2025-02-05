package com.otelrezervasyon.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PattchUpdateBookinTests extends BaseTest{

    @Test
    public void partiallyUpdateBookingTest() {

        JSONObject body = new JSONObject();
        body.put("firstname", "Alisss");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" + createBookingId());

        Assertions.assertEquals("Alisss", response.jsonPath().getJsonObject("firstname"));


    }
}
