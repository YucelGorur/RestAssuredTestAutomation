package com.HotelReservation.test;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void getBookings_with_firstName_filter_test(){
        //rezervasyon olusturulur
       int bookingId = createBookingid();
       //cagrimiza query parametleri eklenir
        spec.queryParam("firstname","User");
        spec.queryParam("lastname","Test");
        //cagriyi gerceklestirilir
        Response response = given(spec)
                .when()
                .get("/booking");

        response
                .then()
                .statusCode(200);

        List<Integer> filtrelenenRezervasyon = response.jsonPath().getList("bookingid");
        Assertions.assertTrue(filtrelenenRezervasyon.contains(bookingId));

    }

}
