package com.portafolio.trip_service.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TripResponse(
    Long id,
    String origin,
    String destination,
    LocalDate departureDate,
    LocalDate arrivalDate,
    BigDecimal price,
    String username
) {}
