package co.edu.udea.tdt.component.roadmap.service.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseAddCmd {

    @NotNull
    private Long courseId;

}
