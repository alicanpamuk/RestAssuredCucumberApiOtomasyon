package com.otelrezervasyon.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest{

    @Test
    public void updateBookingTests() {


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(bookingObject("Özlem", "Duman", 450, false))
                .put("/booking/" + createBookingId());


        String firsName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Özlem", firsName);
        Assertions.assertEquals("Duman", lastName);
        Assertions.assertEquals(450, totalPrice);
        Assertions.assertEquals(false, depositPaid);





    }

}
