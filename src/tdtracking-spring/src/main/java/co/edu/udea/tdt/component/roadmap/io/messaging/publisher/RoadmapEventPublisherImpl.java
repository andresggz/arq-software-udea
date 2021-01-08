package co.edu.udea.tdt.component.roadmap.io.messaging.publisher;

import co.edu.udea.tdt.component.roadmap.io.messaging.publisher.model.RoadmapCreatedEvent;
import co.edu.udea.tdt.component.roadmap.model.Roadmap;
import co.edu.udea.tdt.component.roadmap.service.RoadmapEventPublisher;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class RoadmapEventPublisherImpl implements RoadmapEventPublisher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApplicationEventPublisher publisher;

    @Override
    public void publishRoadmapCreated(@NotNull Roadmap roadmapCreated) {
        logger.debug("Begin publishRoadmapCreated: roadmapCreated = {}", roadmapCreated);

        RoadmapCreatedEvent roadmapCreatedEventToPublish = RoadmapCreatedEvent
                .builder().id(roadmapCreated.getId()).name(roadmapCreated.getName())
                .description(roadmapCreated.getDescription())
                .build();

        publisher.publishEvent(roadmapCreatedEventToPublish);

        logger.debug("End publishRoadmapCreated");
    }
}
