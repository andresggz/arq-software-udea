package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;

import javax.validation.constraints.NotNull;

public interface FleetEventPublisher {

    void publishVehicleAddedToFleet(@NotNull VehicleIdFleet vehicleIdFleetAdded);
}
