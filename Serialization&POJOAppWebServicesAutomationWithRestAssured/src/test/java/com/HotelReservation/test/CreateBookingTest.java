package com.HotelReservation.test;

import com.HotelReservation.models.Booking;
import com.HotelReservation.models.BookingDates;
import com.HotelReservation.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class CreateBookingTest extends BaseTest {

    @Test
    public void createBookingTest (){
        //cagri gerceklestirilir
         Response response = createBooking();

        Assertions.assertEquals("User",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Test",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));

    }
    @Test
    public void createBookingWithPOJO(){
        BookingDates bookingDates = new BookingDates("2023-03-01","2023-03-05");
        Booking booking = new Booking("POJO","Test",400,false,bookingDates,"Sigara icilebilir oda");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("/booking");
        response
                .then()
                .statusCode(200);

        BookingResponse bookingResponse = response.as(BookingResponse.class);
        System.out.println(bookingResponse + "booking response kadedildi");

        Assertions.assertEquals("POJO",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Test",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals("Sigara icilebilir oda",bookingResponse.getBooking().getAdditionalneeds());
    }

}

