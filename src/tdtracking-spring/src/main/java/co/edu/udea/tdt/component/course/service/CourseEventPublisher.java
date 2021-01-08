package co.edu.udea.tdt.component.course.service;

import co.edu.udea.tdt.component.course.model.Course;

import javax.validation.constraints.NotNull;

public interface CourseEventPublisher {

    void publishCourseCreated(@NotNull Course courseCreated);
}
