package com.HotelReservation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void updateBookingTest(){
        //request yapılır
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie","token=" +createToken())
                .body(bookingObject("Yucel","Gorur", 400,false))
                .put("/booking/" +createBookingid());

                //Assertion yazıldı
        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");
        boolean depositpaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Yucel", firstname);
        Assertions.assertEquals("Gorur", lastname);
        Assertions.assertEquals(400, totalprice);
        Assertions.assertEquals(false,depositpaid);
    }

}
