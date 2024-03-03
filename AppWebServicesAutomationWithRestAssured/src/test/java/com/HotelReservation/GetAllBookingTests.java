package com.HotelReservation;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GetAllBookingTests extends BaseTest{
    //Cagri olusturulmalı
    //response kontrolleri
    //curl https://restful-booker.herokuapp.com/booking
    @Test
    public void getAllBookingTest(){

        given(spec)
                .when()
                .get("/booking")
                .then()
                .statusCode(200);
    }

}
