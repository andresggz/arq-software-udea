package co.edu.udea.tdt.component.roadmap.service;

import co.edu.udea.tdt.component.roadmap.model.CourseIdRoadmap;

import javax.validation.constraints.NotNull;

public interface CourseIdRoadmapGateway {

    CourseIdRoadmap register(@NotNull CourseIdRoadmap courseIdRoadmapToRegister);

    CourseIdRoadmap findById(@NotNull Long id);
}
