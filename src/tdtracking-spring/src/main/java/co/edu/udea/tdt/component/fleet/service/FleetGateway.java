package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.Fleet;
import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface FleetGateway {

    Fleet save(@NotNull Fleet fleetToCreate);

    Fleet findById(@NotNull Long id);

    Page<Fleet> findByParameters(@NotNull FleetQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Fleet addVehicle(@NotNull Long fleetId, @NotNull VehicleIdFleet vehicleIdInDataBase);

    Fleet update(@NotNull Fleet fleetToUpdate);

    void deleteById(@NotNull Long id);
}
