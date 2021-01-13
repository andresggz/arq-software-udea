package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;

import javax.validation.constraints.NotNull;

public interface VehicleEventPublisher {

    void publishVehicleCreated(@NotNull Vehicle vehicleCreated);
}
