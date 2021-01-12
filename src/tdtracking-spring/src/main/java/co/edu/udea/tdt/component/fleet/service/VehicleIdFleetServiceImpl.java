package co.edu.udea.tdt.component.fleet.service;

import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.model.VehicleIdSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleIdFleetServiceImpl implements VehicleIdFleetService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VehicleIdFleetGateway vehicleIdFleetGateway;

    @Override
    public VehicleIdFleet registerVehicleId(@NotNull VehicleIdSaveCmd vehicleIdToRegisterCmd) {
        logger.debug("Begin registerVehicleId: vehicleIdToRegisterCmd = {}", vehicleIdToRegisterCmd);

        VehicleIdFleet vehicleIdToRegister = VehicleIdSaveCmd.toModel(vehicleIdToRegisterCmd);

        VehicleIdFleet vehicleIdRegistered = vehicleIdFleetGateway.register(vehicleIdToRegister);

        logger.debug("End registerVehicleId: vehicleIdRegistered = {}", vehicleIdRegistered);
        return vehicleIdRegistered;
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleIdFleet findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        VehicleIdFleet vehicleIdFound = vehicleIdFleetGateway.findById(id);

        logger.debug("End findById: vehicleIdFound = {}", vehicleIdFound);
        return vehicleIdFound;
    }
}
