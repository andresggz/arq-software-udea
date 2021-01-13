package co.edu.udea.tdt.component.fleet.io.messaging.receiver;

import co.edu.udea.tdt.component.fleet.service.VehicleIdFleetService;
import co.edu.udea.tdt.component.fleet.service.model.VehicleIdSaveCmd;
import co.edu.udea.tdt.component.vehicle.io.messaging.publisher.model.VehicleCreatedEvent;
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
public class FleetEventReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VehicleIdFleetService vehicleIdFleetService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void handleVehicleCreatedEvent(VehicleCreatedEvent event){
        logger.debug("Begin handleVehicleCreatedEvent: event = {}", event);

        VehicleIdSaveCmd vehicleIdToRegisterCmd = VehicleIdSaveCmd.builder()
                .id(event.getId()).name(event.getName()).
                        description(event.getDescription()).build();

        vehicleIdFleetService.registerVehicleId(vehicleIdToRegisterCmd);

        logger.debug("End handleVehicleCreatedEvent");
    }
}
