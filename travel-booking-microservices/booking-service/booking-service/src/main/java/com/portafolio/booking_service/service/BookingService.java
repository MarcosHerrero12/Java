package com.portafolio.booking_service.service;

import com.portafolio.booking_service.mapper.BookingMapper;
import com.portafolio.booking_service.request.CreateBookingRequest;
import com.portafolio.booking_service.response.BookingResponse;
import com.portafolio.booking_service.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private final BookingMapper bookingMapper;
    private final JwtUtil jwtUtil;

    public BookingService(BookingMapper bookingMapper, JwtUtil jwtUtil) {
        this.bookingMapper = bookingMapper;
        this.jwtUtil = jwtUtil;
    }

    public void createBooking(CreateBookingRequest request, String authHeader) {
        //String username = jwtUtil.extractUsername(authHeader.substring(7));

        bookingMapper.insertBooking(request, authHeader);

    }

    public List<BookingResponse> getBookingsForUser(String authHeader) {
        String username = jwtUtil.extractUsername(authHeader.substring(7));
        List<BookingResponse> bookings = bookingMapper.findBookingsByUsername(username);
        return bookings;
    }
}
