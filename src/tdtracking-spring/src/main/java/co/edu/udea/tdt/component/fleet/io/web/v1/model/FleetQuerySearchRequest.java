package co.edu.udea.tdt.component.fleet.io.web.v1.model;

import co.edu.udea.tdt.component.fleet.service.model.FleetQuerySearchCmd;
import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FleetQuerySearchRequest {

    private String name;

    private Boolean active;

    public static FleetQuerySearchCmd toModel(FleetQuerySearchRequest queryCriteria){
        return FleetQuerySearchCmd.builder().name(queryCriteria.getName())
                    .active(queryCriteria.getActive())
                    .build();
    }
}
