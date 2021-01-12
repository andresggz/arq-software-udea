package co.edu.udea.tdt.component.vehicle.io.web.v1.model;

import co.edu.udea.tdt.component.vehicle.service.model.VehicleQuerySearchCmd;
import lombok.*;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleQuerySearchRequest {

    private String name;

    private Boolean active;

    private Boolean isLinkedToRoute;

    public static VehicleQuerySearchCmd toModel(VehicleQuerySearchRequest queryCriteria){
        return VehicleQuerySearchCmd.builder().name(queryCriteria.getName())
                    .active(queryCriteria.getActive())
                    .isLinkedToRoute(queryCriteria.getIsLinkedToRoute())
                    .build();
    }
}
