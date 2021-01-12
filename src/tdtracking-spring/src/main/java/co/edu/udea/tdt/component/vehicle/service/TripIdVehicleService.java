package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.service.model.TripIdSaveCmd;

import javax.validation.constraints.NotNull;

public interface TripIdVehicleService {

    TripIdVehicle registerTripId(@NotNull TripIdSaveCmd tripIdToRegisterCmd);

    TripIdVehicle findById(@NotNull Long id);
}
