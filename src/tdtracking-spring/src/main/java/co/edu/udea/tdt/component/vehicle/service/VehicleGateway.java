package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface VehicleGateway {

    Vehicle save(@NotNull Vehicle vehicleToCreate);

    Vehicle findById(@NotNull Long id);

    Page<Vehicle> findByParameters(@NotNull VehicleQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Vehicle addTrip(@NotNull Long tripId, @NotNull TripIdVehicle tripIdVehicleInDataBase);

    Vehicle update(@NotNull Vehicle vehicleToUpdate);

    void deleteById(@NotNull Long id);
}
