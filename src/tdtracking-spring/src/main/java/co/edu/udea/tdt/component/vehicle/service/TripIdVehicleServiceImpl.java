package co.edu.udea.tdt.component.vehicle.service;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.service.model.TripIdSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class TripIdVehicleServiceImpl implements TripIdVehicleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TripIdVehicleGateway tripIdVehicleGateway;

    @Override
    public TripIdVehicle registerTripId(@NotNull TripIdSaveCmd tripIdToRegisterCmd) {
        logger.debug("Begin registerTripId: tripIdRegisteredCmd = {}", tripIdToRegisterCmd);

        TripIdVehicle tripIdVehicleToRegister = TripIdSaveCmd.toModel(tripIdToRegisterCmd);

        TripIdVehicle tripIdVehicleRegistered = tripIdVehicleGateway.register(tripIdVehicleToRegister);

        logger.debug("End registerTripId = {}", tripIdVehicleRegistered);
        return tripIdVehicleRegistered;
    }

    @Override
    @Transactional(readOnly = true)
    public TripIdVehicle findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        TripIdVehicle tripIdVehicleFound = tripIdVehicleGateway.findById(id);

        logger.debug("End findById: tripIdVehicleFound = {}", tripIdVehicleFound);
        return tripIdVehicleFound;
    }
}
