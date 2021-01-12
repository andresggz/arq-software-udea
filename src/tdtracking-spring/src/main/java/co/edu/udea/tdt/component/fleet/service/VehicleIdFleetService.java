package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.model.VehicleIdSaveCmd;

import javax.validation.constraints.NotNull;

public interface VehicleIdFleetService {

    VehicleIdFleet registerVehicleId(@NotNull VehicleIdSaveCmd vehicleIdToRegisterCmd);

    VehicleIdFleet findById(@NotNull Long id);
}
