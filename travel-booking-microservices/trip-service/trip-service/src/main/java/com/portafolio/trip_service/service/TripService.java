package com.portafolio.trip_service.service;

import com.portafolio.trip_service.model.Trip;
import com.portafolio.trip_service.repository.TripRepository;
import com.portafolio.trip_service.request.CreateTripRequest;
import com.portafolio.trip_service.request.UpdateTripRequest;
import com.portafolio.trip_service.response.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public TripResponse createTrip(CreateTripRequest request, String username) {
        if (request.getDepartureDate().isAfter(request.getArrivalDate())) {
            throw new IllegalArgumentException("La fecha de salida no puede ser posterior a la fecha de llegada.");
        }

        Trip trip = Trip.builder()
                .origin(request.getOrigin())
                .destination(request.getDestination())
                .departureDate(request.getDepartureDate())
                .arrivalDate(request.getArrivalDate())
                .price(request.getPrice())
                .username(username) // 👈 Asignamos el username del JWT
                .build();

        return toResponse(tripRepository.save(trip));
    }

    public TripResponse updateTrip(Long id, UpdateTripRequest request, String username) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getUsername().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para modificar este viaje");
        }

        if (request.getDepartureDate().isAfter(request.getArrivalDate())) {
            throw new IllegalArgumentException("La fecha de salida no puede ser posterior a la fecha de llegada.");
        }

        trip.setOrigin(request.getOrigin());
        trip.setDestination(request.getDestination());
        trip.setDepartureDate(request.getDepartureDate());
        trip.setArrivalDate(request.getArrivalDate());
        trip.setPrice(request.getPrice());

        return toResponse(tripRepository.save(trip));
    }



    public void deleteTrip(Long id, String username) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getUsername().equals(username)) {
            throw new AccessDeniedException("No tienes permiso para eliminar este viaje");
        }

        tripRepository.delete(trip);
    }
    public List<Trip> getTripsByUsername(String username) {
        return tripRepository.findAllByUsername(username);
    }
    public TripResponse getTripById(Long id, String username) {
        Trip trip = tripRepository.findByIdAndUsername(id, username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        return new TripResponse(
                trip.getId(),
                trip.getOrigin(),
                trip.getDestination(),
                trip.getDepartureDate(),
                trip.getArrivalDate(),
                trip.getPrice(),
                trip.getUsername()
        );
    }



    private TripResponse toResponse(Trip trip) {
        return new TripResponse(
                trip.getId(),
                trip.getOrigin(),
                trip.getDestination(),
                trip.getDepartureDate(),
                trip.getArrivalDate(),
                trip.getPrice(),
                trip.getUsername()
        );
    }
}
