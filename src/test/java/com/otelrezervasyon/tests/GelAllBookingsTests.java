package com.otelrezervasyon.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GelAllBookingsTests extends BaseTest {
    // cagriyi olusutr
    // response kontrolleri
    //curl -i https://restful-booker.herokuapp.com/booking
    @Test
    public void getAllBokingTest() {
        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }
    @Test
    public void getBookings_with_firstname_filter_test(){
        //Yeni rezervasyon olustur

        int bookingId = createBookingId();

        //Cagrimiza Query Parametresi ekle

        spec.queryParam("firstname","Ali");
        spec.queryParam("lastname","Pamuk");
        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> list = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(list.contains(bookingId));

    }
}
