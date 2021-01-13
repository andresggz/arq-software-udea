package co.edu.udea.tdt.component.vehicle.io.messaging.receiver;

import co.edu.udea.tdt.component.fleet.io.messaging.publisher.model.VehicleAddedToFleetEvent;
import co.edu.udea.tdt.component.trip.io.messaging.publisher.model.TripCreatedEvent;
import co.edu.udea.tdt.component.vehicle.service.TripIdVehicleService;
import co.edu.udea.tdt.component.vehicle.service.VehicleService;
import co.edu.udea.tdt.component.vehicle.service.model.TripIdSaveCmd;
import co.edu.udea.tdt.component.vehicle.service.model.VehicleAddedToFleetCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class VehicleEventReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TripIdVehicleService tripIdVehicleService;

    private final VehicleService vehicleService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void handleTripCreatedEvent(TripCreatedEvent event){
        logger.debug("Begin handleTripCreatedEvent: event = {}", event);

        TripIdSaveCmd tripIdToRegisterCmd = TripIdSaveCmd.builder()
                .id(event.getId()).name(event.getName()).description(event.getDescription())
                .active(event.getActive()).iconId(event.getIconId()).level(event.getLevel())
                .build();

        tripIdVehicleService.registerTripId(tripIdToRegisterCmd);

        logger.debug("End handleTripCreatedEvent");
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void handleVehicleAddedToFleetEvent(VehicleAddedToFleetEvent event){
        logger.debug("Begin handleVehicleAddedToFleetEvent: event = {}", event);

        VehicleAddedToFleetCmd vehicleAddedToFleetCmd = VehicleAddedToFleetCmd.builder()
                .id(event.getId())
                .build();

        vehicleService.update(vehicleAddedToFleetCmd);
        logger.debug("End handleVehicleAddedToFleetEvent");
    }
}
