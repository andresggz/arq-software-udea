package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;

import javax.validation.constraints.NotNull;

public interface TripIdVehicleGateway {

    TripIdVehicle register(@NotNull TripIdVehicle tripIdVehicleToRegister);

    TripIdVehicle findById(@NotNull Long id);
}
