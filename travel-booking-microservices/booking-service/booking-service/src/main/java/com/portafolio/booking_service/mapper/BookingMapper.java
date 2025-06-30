package com.portafolio.booking_service.mapper;

import com.portafolio.booking_service.entity.Booking;
import com.portafolio.booking_service.request.CreateBookingRequest;
import com.portafolio.booking_service.response.BookingResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookingMapper {
    
    @Select("SELECT * FROM bookings WHERE username = #{username}")
    List<BookingResponse> findBookingsByUsername(String username);

    @Insert("INSERT INTO bookings (trip_id, user_id, booking_date) VALUES (#{tripId}, #{userId}, #{bookingDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertBooking(CreateBookingRequest request,String authHeader );
}
