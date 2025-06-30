package com.portafolio.trip_service.controller;

import com.portafolio.trip_service.model.Trip;
import com.portafolio.trip_service.request.CreateTripRequest;
import com.portafolio.trip_service.request.UpdateTripRequest;
import com.portafolio.trip_service.response.TripResponse;
import com.portafolio.trip_service.security.JwtUtil;
import com.portafolio.trip_service.service.TripService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@AllArgsConstructor
public class TripController {
    private final TripService tripService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/create")
    public ResponseEntity<TripResponse> createTrip(@Valid @RequestBody CreateTripRequest tripRequest, Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(tripService.createTrip(tripRequest, username));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<TripResponse> updateTrip(
            @PathVariable Long id,
            @RequestBody UpdateTripRequest request,
            Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(tripService.updateTrip(id, request, username));
    }


    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        tripService.deleteTrip(id, username);
        return ResponseEntity.noContent().build();
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/myTrips")
    public ResponseEntity<List<TripResponse>> getUserTrips(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        String username = jwtUtil.extractUsername(jwt); // 👈 Extraemos el username del JWT

        List<Trip> trips = tripService.getTripsByUsername(username);

        List<TripResponse> response = trips.stream()
                .map(trip -> new TripResponse(
                        trip.getId(),
                        trip.getOrigin(),
                        trip.getDestination(),
                        trip.getDepartureDate(),
                        trip.getArrivalDate(),
                        trip.getPrice(),
                        trip.getUsername()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TripResponse> getTripById(
            @PathVariable Long id,
            Authentication authentication) {
        String username = authentication.getName(); // ✅ obtiene el username
        TripResponse response = tripService.getTripById(id, username);
        return ResponseEntity.ok(response);
    }





}
