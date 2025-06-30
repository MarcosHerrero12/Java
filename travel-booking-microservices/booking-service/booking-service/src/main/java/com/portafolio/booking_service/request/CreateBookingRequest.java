package com.portafolio.booking_service.request;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreateBookingRequest {
    private Long tripId;
    //private String username;
    private LocalDateTime bookingDate;
}
