package co.edu.udea.tdt.component.trip.io.messaging.publisher;

import co.edu.udea.tdt.component.trip.io.messaging.publisher.model.TripCreatedEvent;
import co.edu.udea.tdt.component.trip.model.Trip;
import co.edu.udea.tdt.component.trip.service.TripEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class TripEventPublisherImpl implements TripEventPublisher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationEventPublisher publisher;


    @Override
    public void publishTripCreated(@NotNull Trip tripCreated) {
        logger.debug("Begin publishTripCreated: tripCreated = {}", tripCreated);

        TripCreatedEvent tripCreatedEventToPublish = TripCreatedEvent
                .builder().id(tripCreated.getId()).name(tripCreated.getName())
                .description(tripCreated.getDescription())
                .active(tripCreated.getActive())
                .iconId(tripCreated.getIconId())
                .level(tripCreated.getLevel().name())
                .build();

        publisher.publishEvent(tripCreatedEventToPublish);

        logger.debug("End publishTripCreated");
    }
}
