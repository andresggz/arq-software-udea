package co.edu.udea.tdt.component.vehicle.io.messaging.publisher;

import co.edu.udea.tdt.component.vehicle.io.messaging.publisher.model.VehicleCreatedEvent;
import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import co.edu.udea.tdt.component.vehicle.service.VehicleEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class VehicleEventPublisherImpl implements VehicleEventPublisher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationEventPublisher publisher;

    @Override
    public void publishVehicleCreated(@NotNull Vehicle vehicleCreated) {
        logger.debug("Begin publishVehicleCreated: vehicleCreated = {}", vehicleCreated);

        VehicleCreatedEvent vehicleCreatedEventToPublish = VehicleCreatedEvent
                .builder().id(vehicleCreated.getId()).name(vehicleCreated.getName())
                .description(vehicleCreated.getDescription())
                .build();

        publisher.publishEvent(vehicleCreatedEventToPublish);

        logger.debug("End publishVehicleCreated");
    }
}
