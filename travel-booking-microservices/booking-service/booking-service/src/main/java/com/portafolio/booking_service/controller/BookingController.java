package com.portafolio.booking_service.controller;

import com.portafolio.booking_service.request.CreateBookingRequest;
import com.portafolio.booking_service.response.BookingResponse;
import com.portafolio.booking_service.service.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/createBooking")
    public ResponseEntity createBooking(@RequestBody CreateBookingRequest request, Authentication auth) {
        String username = auth.getName();
        bookingService.createBooking(request, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingResponse>> getUserBookings(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(bookingService.getBookingsForUser(authHeader));
    }
}
