package com.portafolio.booking_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private Long id;
    private Long tripId;
    private String username;
    private LocalDateTime bookingDate;
}
