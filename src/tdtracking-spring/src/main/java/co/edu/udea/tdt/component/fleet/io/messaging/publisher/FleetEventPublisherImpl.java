package co.edu.udea.tdt.component.fleet.io.messaging.publisher;

import co.edu.udea.tdt.component.fleet.io.messaging.publisher.model.VehicleAddedToFleetEvent;
import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import co.edu.udea.tdt.component.fleet.service.FleetEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class FleetEventPublisherImpl implements FleetEventPublisher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationEventPublisher publisher;

    @Override
    public void publishVehicleAddedToFleet(@NotNull VehicleIdFleet vehicleIdFleetAdded) {
        logger.debug("Begin publishVehicleAddedToFleet: vehicleIdFleetAdded = {}", vehicleIdFleetAdded);

        VehicleAddedToFleetEvent vehicleAdded = VehicleAddedToFleetEvent.builder()
                .id(vehicleIdFleetAdded.getId())
                .build();

        publisher.publishEvent(vehicleAdded);
        logger.debug("End publishVehicleAddedToFleet");
    }
}
