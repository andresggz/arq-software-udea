package co.edu.udea.tdt.component.roadmap.service;

import co.edu.udea.tdt.component.roadmap.model.Roadmap;

import javax.validation.constraints.NotNull;

public interface RoadmapEventPublisher {

    void publishRoadmapCreated(@NotNull Roadmap roadmapCreated);
}
