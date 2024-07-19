package com.otelrezervasyon.tests;

import com.otelrezervasyon.models.Booking;
import com.otelrezervasyon.models.BookingDates;
import com.otelrezervasyon.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreatBookingTests extends BaseTest{


    @Test
    public void createBookingTest() {

        Response response = createBooking();

        Assertions.assertEquals("Ali", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Pamuk", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(300, response.jsonPath().getInt("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getBoolean("booking.depositpaid"));
        Assertions.assertEquals("2024-01-01", response.jsonPath().getJsonObject("booking.bookingdates.checkin"));
        Assertions.assertEquals("2024-01-10", response.jsonPath().getJsonObject("booking.bookingdates.checkout"));

    }
    @Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2024-03-01","2024-03-05");
        Booking booking = new Booking("Ali","Pamuk",1000,false,bookingDates,"Sigara icilebilir oda");
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse+"Booking response kaydedildi");
        Assertions.assertEquals("Ali",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Pamuk",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Sigara icilebilir oda",bookingResponse.getBooking().getAdditionalneeds());

    }
}
