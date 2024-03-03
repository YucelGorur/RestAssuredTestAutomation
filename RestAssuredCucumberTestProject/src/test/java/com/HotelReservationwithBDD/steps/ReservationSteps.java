package com.HotelReservationwithBDD.steps;

import com.HotelReservationwithBDD.models.BookingResponse;
import com.HotelReservationwithBDD.services.ReservationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class ReservationSteps {
    ReservationService reservationService;
    String authKey;
    BookingResponse bookingResponse;

    @Given("kullanici yeni bir rezervasyon olusturuyor")
    public void cagriBaslangici(){
        reservationService = new ReservationService();
    }
    @Given("kullanici rezerasyon icin gereken bilgileri veriyor")
    public void createAuth(){
        authKey = reservationService.generateToken();
    }
    @When("kullanici otel rezervasyonu yaratiyor")
    public void createReservation(){
        bookingResponse = reservationService.createBooking();
    }

    @Then("rezervasyon basarili sekilde olusuturuldu")
    public void reservationAssertions(){
        Assertions.assertEquals("BDD",bookingResponse.getBooking().getFirstname());
        Assertions.assertEquals("Cucumber",bookingResponse.getBooking().getLastname());
        Assertions.assertEquals(100,bookingResponse.getBooking().getTotalprice());
        Assertions.assertFalse(bookingResponse.getBooking().isDepositpaid());
        Assertions.assertEquals("kedi kumu",bookingResponse.getBooking().getAdditionalneeds());
    }

    @Then("Kullanici olusturulan rezervasyonu iptal ediyor")
    public void cancelReservation(){
        reservationService.deleteReservation(authKey,bookingResponse.getBookingid());
    }
}
