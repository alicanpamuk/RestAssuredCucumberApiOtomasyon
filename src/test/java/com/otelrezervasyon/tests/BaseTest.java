package com.otelrezervasyon.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    @BeforeEach
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())) //otomatik response artık yazacak.
                .build();
    }

    protected int createBookingId() {
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }

    protected Response createBooking() {

        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Ali", "Pamuk", 300, true))
                .post("/booking");
        //response.prettyPrint();

        response
                .then()
                .statusCode(200);
        return response;
    }

    protected String bookingObject(String firstName, String lastName, int totalPrice, boolean depositPaid) {
        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("totalprice", totalPrice);
        body.put("depositpaid", depositPaid);
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2024-01-01");
        bookingDates.put("checkout", "2024-01-10");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Lunch");
        return body.toString();


    }

    protected String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");
        response.prettyPrint();
        return response.jsonPath().getJsonObject("token");
    }
}
