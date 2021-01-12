package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.model.TripAddCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleAddedToFleetCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface VehicleService {

    Vehicle create(@NotNull VehicleSaveCmd vehicleToCreateCmd);

    Vehicle findById(@NotNull Long id);

    Page<Vehicle> findByParameters(@NotNull VehicleQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Vehicle addTrip(@NotNull Long vehicleId, @NotNull TripAddCmd tripToAddCmd);

    Vehicle update(@NotNull VehicleAddedToFleetCmd vehicleAddedToFleetCmd);

    void deleteById(@NotNull Long id);

    List<Vehicle> createFromSheets(@NotNull MultipartFile vehiclesToCreateFromSheets);
}
