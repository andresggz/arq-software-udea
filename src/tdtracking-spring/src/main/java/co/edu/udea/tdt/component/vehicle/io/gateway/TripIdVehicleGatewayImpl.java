package co.edu.udea.tdt.component.vehicle.io.gateway;

import co.edu.udea.tdt.component.vehicle.io.repository.TripIdVehicleRepository;
import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import co.edu.udea.tdt.component.vehicle.service.TripIdVehicleGateway;
import co.edu.udea.tdt.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class TripIdVehicleGatewayImpl implements TripIdVehicleGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String RESOURCE_NOT_FOUND = "TripIdVehicle not found.";

    private final TripIdVehicleRepository tripIdVehicleRepository;

    @Override
    public TripIdVehicle register(@NotNull TripIdVehicle tripIdVehicleToRegister) {
        logger.debug("Begin register: tripIdVehicleToRegister = {}", tripIdVehicleToRegister);

        TripIdVehicle tripIdVehicleRegistered = tripIdVehicleRepository.save(tripIdVehicleToRegister);

        logger.debug("End register: tripIdVehicleRegistered = {}", tripIdVehicleRegistered);
        return tripIdVehicleRegistered;
    }

    @Override
    public TripIdVehicle findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        TripIdVehicle tripIdVehicleFound = tripIdVehicleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: tripIdVehicleFound = {}", tripIdVehicleFound);
        return tripIdVehicleFound;
    }
}
