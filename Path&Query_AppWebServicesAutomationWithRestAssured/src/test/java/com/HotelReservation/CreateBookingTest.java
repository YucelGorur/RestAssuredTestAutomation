package com.HotelReservation;

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
}
