package com.HotelReservation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTest extends BaseTest {

    @Test
    public void partiallyUpdateBookingTest(){
        //rezervasyon içerisindeki patch tip ile değerler değiştirilir

        JSONObject body = new JSONObject();
        body.put("firstname","Ali");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" +createToken())
                .body(body.toString())
                .when()
                .patch("/booking/" +createBookingid());


        // assertions yazılır
        Assertions.assertEquals("Ali",response.jsonPath().getJsonObject("firstname"));

    }
}
