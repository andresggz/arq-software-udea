package co.edu.udea.tdt.component.trip.service;

import co.edu.udea.tdt.component.trip.model.Trip;

import javax.validation.constraints.NotNull;

public interface TripEventPublisher {

    void publishTripCreated(@NotNull Trip tripCreated);
}
