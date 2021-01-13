package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.Fleet;
import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
import co.edu.udea.tdt.component.fleet.service.model.FleetSaveCmd;
import co.edu.udea.tdt.component.fleet.service.model.VehicleAddCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface FleetService {

    Fleet create(@NotNull FleetSaveCmd vehicleToCreateCmd);

    Fleet findById(@NotNull Long id);

    Page<Fleet> findByParameters(@NotNull FleetQuerySearchCmd queryCriteria, @NotNull Pageable pageable);

    Fleet addVehicle(@NotNull Long fleetId, @NotNull VehicleAddCmd vehicleToAddCmd);

    List<Fleet> createFromSheets(@NotNull MultipartFile fleetsToCreateFromSheets);

    Fleet update(@NotNull Long id, @NotNull FleetSaveCmd fleetToUpdateCmd);

    void deleteById(@NotNull Long id);
}
