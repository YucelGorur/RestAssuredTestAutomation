package com.HotelReservationwithBDD.services;

import com.HotelReservationwithBDD.models.Auth;
import com.HotelReservationwithBDD.models.Booking;
import com.HotelReservationwithBDD.models.BookingDates;
import com.HotelReservationwithBDD.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReservationService extends BaseTest {
    //token olusturulur
    public String generateToken(){
        Auth authBody = new Auth("admin","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(authBody)
                .post("/auth");

        response
                .then()
                .statusCode(200);

        return  response.jsonPath().getJsonObject("token");
    }

    //rezervasyon olusturulur

    public BookingResponse createBooking(){
        BookingDates bookingDates = new BookingDates("2024-01-01","2024-01-10");
        Booking booking = new Booking("BDD","Cucumber",100,false, bookingDates,"kedi kumu");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(booking)
                .post("/booking");

        response
                .then()
                .statusCode(200);
        return response.as(BookingResponse.class);
    }
    //erzervasyon silinir

    public void deleteReservation(String token, int bookingId){
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + token)
                .when()
                .delete("/booking/"+bookingId);
        response
                .then()
                .statusCode(201);

    }

}
