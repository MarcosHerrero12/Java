package com.portafolio.booking_service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private Long tripId;
    private String username;
    private LocalDateTime createdAt;


}
