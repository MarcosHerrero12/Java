package com.portafolio.trip_service.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTripRequest {

    @NotBlank(message = "El origen no puede estar vacío")
    private String origin;

    @NotBlank(message = "El destino no puede estar vacío")
    private String destination;

    @NotNull(message = "La fecha de salida es obligatoria")
    @FutureOrPresent(message = "La fecha de salida debe ser hoy o en el futuro")
    private LocalDate departureDate;

    @NotNull(message = "La fecha de llegada es obligatoria")
    private LocalDate arrivalDate;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private BigDecimal price;
}


