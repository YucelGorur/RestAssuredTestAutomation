package com.HotelReservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBooking(){
        //delete cagrisi
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" +createToken())
                .when()
                .delete( "booking/" + createBookingid());

        //assertions olusturma
        response
                .then()
                .statusCode(201);





    }
}
