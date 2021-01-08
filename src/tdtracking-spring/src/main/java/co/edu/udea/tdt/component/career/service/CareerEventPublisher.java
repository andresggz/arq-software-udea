package co.edu.udea.tdt.component.career.service;

import co.edu.udea.tdt.component.career.model.RoadmapIdCareer;

import javax.validation.constraints.NotNull;

public interface CareerEventPublisher {

    void publishRoadmapAddedToCareer(@NotNull RoadmapIdCareer roadmapIdCareerAdded);
}
