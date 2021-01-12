package co.edu.udea.tdt.component.fleet.io.gateway;

import co.edu.udea.tdt.component.fleet.io.repository.VehicleIdFleetRepository;
import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.VehicleIdFleetGateway;
import co.edu.udea.tdt.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
@RequiredArgsConstructor
public class VehicleIdFleetGatewayImpl implements VehicleIdFleetGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String RESOURCE_NOT_FOUND = "VehicleIdFleet not found.";

    private final VehicleIdFleetRepository vehicleIdRepository;

    @Override
    public VehicleIdFleet register(@NotNull VehicleIdFleet vehicleIdToRegister) {
        logger.debug("Begin register: vehicleIdToRegister = {}", vehicleIdToRegister);

        final VehicleIdFleet vehicleIdRegistered =  vehicleIdRepository.save(vehicleIdToRegister);

        logger.debug("End register: vehicleIdRegistered = {}", vehicleIdRegistered);
        return vehicleIdRegistered;
    }

    @Override
    public VehicleIdFleet findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final VehicleIdFleet vehicleIdFound = vehicleIdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: vehicleIdFound = {}", vehicleIdFound);
        return vehicleIdFound;
    }
}
