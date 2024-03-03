package com.HotelReservation;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import java.util.Arrays;
import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    @BeforeEach
    public void setup(){ //baseurl sadelestirilen ve req - resp degerlerini console yazdirilan method
        spec = new RequestSpecBuilder()
        .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())) //req - resp bilgilerini console loglar
                .build();
        }


    //Body olusturulur
    protected String bookingObject(String firstname, String lastname,int totalprice, boolean depositpaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname", lastname);
        body.put("totalprice",totalprice);
        body.put("depositpaid",depositpaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-10-10");
        bookingDates.put("checkout","2024-10-15");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Evcil Hayvan Kabul Edilen Oda");

        return body.toString();
    }
        //Response objesi oluşturulur
    protected Response createBooking(){
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("User","Test",200,true))
                .post("/booking");

        //assertionlar yazılır
        response
                .then()
                .statusCode(200);
        return response;
    }
    //bookingid degeri alınır
    protected int createBookingid(){
        Response response = createBooking();
        return  response.jsonPath().getJsonObject("bookingid");
    }

    //token oluşturma metodu
    protected String createToken(){
        JSONObject body = new JSONObject();

        body.put("username", "admin");
        body.put("password", "password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post("auth");

        return response.jsonPath().getJsonObject("token");
    }
}
