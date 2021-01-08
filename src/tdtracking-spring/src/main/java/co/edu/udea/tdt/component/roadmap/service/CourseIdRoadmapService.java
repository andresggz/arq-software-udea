package co.edu.udea.tdt.component.roadmap.service;

import co.edu.udea.tdt.component.roadmap.model.CourseIdRoadmap;
import co.edu.udea.tdt.component.roadmap.service.model.CourseIdSaveCmd;

import javax.validation.constraints.NotNull;

public interface CourseIdRoadmapService {

    CourseIdRoadmap registerCourseId(@NotNull CourseIdSaveCmd courseIdToRegisterCmd);

    CourseIdRoadmap findById(@NotNull Long id);
}
