package co.edu.udea.tdt.component.career.service.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoadmapAddCmd {

    @NotNull
    private Long roadmapId;

}
